package ca.uqtr.patient.service.patient;


import ca.uqtr.patient.dto.MedicalFileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.Response;
import ca.uqtr.patient.dto.Error;
import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.ClinicalExaminationDto;
import ca.uqtr.patient.entity.*;
import ca.uqtr.patient.repository.professional.ProfessionalRepository;
import ca.uqtr.patient.repository.medicalFile.MedicalFileRepository;
import ca.uqtr.patient.repository.patient.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.bytecode.stackmap.TypeData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PatientServiceImpl implements PatientService {
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

    private PatientRepository patientRepository;
    private final MedicalFileRepository medicalFileRepository;
    private final ProfessionalRepository professionalRepository;
    private ModelMapper modelMapper;
    private MessageSource messageSource;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, MedicalFileRepository medicalFileRepository, ProfessionalRepository professionalRepository, MessageSource messageSource) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.medicalFileRepository = medicalFileRepository;
        this.professionalRepository = professionalRepository;
        this.messageSource = messageSource;
    }

    @Override
    public Response addPatient(PatientDto patientDto, String id) {
        try {
            System.out.println(patientDto);
            System.out.println(id);
                Patient patient = patientDto.dtoToObj(modelMapper);
                Professional professional = professionalRepository.getProfessionalById(UUID.fromString(id));
                if (professional == null){
                    professional = professionalRepository.save(new Professional(UUID.fromString(id), true));
                }
                Set<Professional> professionals = patient.getProfessionals();
                professionals.add(professional);
                patient.setProfessionals(professionals);
                patient.setFileNumber();
                Patient patient_db = patientRepository.save(patient);

                MedicalFile medicalFile = new MedicalFile();
                medicalFile.setPatient(patient_db.getId().toString());
                medicalFile.setCreationDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                medicalFileRepository.save(medicalFile);

                return new Response(modelMapper.map(patient_db, PatientDto.class), null);

        } catch (Exception ex){
            LOGGER.log( Level.WARNING, ex.getMessage());
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.null.id", null, Locale.US)),
                            messageSource.getMessage("error.null.message", null, Locale.US)));
        }
    }

    @Override
    public Response getPatient(PatientDto patientDto) {
        try {
            System.out.println(patientDto.toString());
            System.out.println(patientDto.dtoToObj(modelMapper).getId());
            System.out.println(patientDto.dtoToObj(modelMapper).getFirstName());
            PatientDto pDto = new PatientDto();
            UUID patientId = patientDto.dtoToObj(modelMapper).getId();
            Optional<Patient> patient = patientRepository.findById(patientId);
            MedicalFile medicalFile = medicalFileRepository.getMedicalFileByPatient(patientId.toString());
            MedicalFileDto medicalFileDto = modelMapper.map(medicalFile, MedicalFileDto.class);
            pDto.setMedicalFile(medicalFileDto);
            return patient.map(value -> new Response(modelMapper.map(value, PatientDto.class), null)).orElseGet(() -> new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.patient.exist.id", null, Locale.US)),
                            messageSource.getMessage("error.patient.exist.message", null, Locale.US))));

        } catch (Exception e){
            LOGGER.log( Level.WARNING, e.getMessage());
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.null.id", null, Locale.US)),
                            messageSource.getMessage("error.null.message", null, Locale.US)));
        }
    }

    @Override
    public Response getPatients() {
        return new Response(patientRepository.findAll(), null);
    }

    @Override
    public Response getPatientsByProfessional(String id) {
        return new Response(patientRepository.findByProfessionals(professionalRepository.getProfessionalById(UUID.fromString(id))), null);
    }

    @Override
    public Response getPatientSocioDemographicVariables(String patientId) {
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileByPatient(patientId);
        String socio = medicalFile.getSocioDemographicVariables();
        if (socio == null)
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.patient.socio.exist.id", null, Locale.US)),
                            messageSource.getMessage("error.patient.socio.exist.message", null, Locale.US)));
        //return new Response(modelMapper.map(socio, SocioDemographicVariablesDto.class), null);
        return new Response(socio, null);
    }

    @Override
    public Response addSocioDemographicVariables(String patientId, String socioDemographicVariablesDto) {
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileByPatient(patientId);
        medicalFile.setSocioDemographicVariables(socioDemographicVariablesDto);
        return new Response(modelMapper.map(medicalFileRepository.save(medicalFile), MedicalFileDto.class), null);
    }

    @Override
    public Response getPatientAntecedents(String patientId) {
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileWith_MedicalFileHistory_FetchTypeEAGER(patientId);
        List<MedicalFileHistory> medicalFileHistories = medicalFile.getMedicalFileHistory();
        if (medicalFileHistories == null)
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.patient.antecedents.exist.id", null, Locale.US)),
                            messageSource.getMessage("error.patient.antecedents.exist.message", null, Locale.US)));
        return new Response(medicalFileHistories, null);
    }

    @Override
    public Response addAntecedents(String patientId, String antecedentsDto) {
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileWith_MedicalFileHistory_FetchTypeEAGER(patientId);
        MedicalFileHistory medicalFileHistory = new MedicalFileHistory(new java.sql.Date(Calendar.getInstance().getTimeInMillis()), antecedentsDto);
        List<MedicalFileHistory> medicalFileHistories = medicalFile.getMedicalFileHistory();
        medicalFileHistories.add(medicalFileHistory);
        medicalFile.setMedicalFileHistory(medicalFileHistories);
        return  new Response(modelMapper.map(medicalFileRepository.save(medicalFile), MedicalFileDto.class), null);
    }

    @Override
    public Response getPatientClinicalExaminationList(String patientId) {
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileWith_ClinicalExamination_FetchTypeEAGER(patientId);
        System.out.println(medicalFile.toString());
        List<ClinicalExamination> clinicalExamination = medicalFile.getClinicalExamination();
        if (clinicalExamination == null)
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.patient.ce.exist.id", null, Locale.US)),
                            messageSource.getMessage("error.patient.ce.exist.message", null, Locale.US)));
        return new Response(clinicalExamination, null);
    }

    @Override
    public Response addClinicalExamination(String patientId, ClinicalExaminationDto clinicalExaminationDto) {
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileWith_ClinicalExamination_FetchTypeEAGER(patientId);
        System.out.println(medicalFile.toString());
        List<ClinicalExamination> clinicalExamination = medicalFile.getClinicalExamination();
        clinicalExamination.add(clinicalExaminationDto.dtoToObj(modelMapper));
        medicalFile.setClinicalExamination(clinicalExamination);
        return  new Response(modelMapper.map(medicalFileRepository.save(medicalFile), MedicalFileDto.class), null);
    }


    @Override
    public Response updatePatient(PatientDto patientDto) {
        try {
            Patient patient = patientDto.dtoToObj(modelMapper);
            patientRepository.save(patient);
            return new Response(modelMapper.map(modelMapper.map(patient, PatientDto.class), PatientDto.class), null);
        } catch (Exception ex){
            LOGGER.log( Level.WARNING, ex.getMessage());
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.null.id", null, Locale.US)),
                            messageSource.getMessage("error.null.message", null, Locale.US)));
        }
    }


}
