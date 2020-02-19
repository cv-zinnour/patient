package ca.uqtr.patient.service.patient;


import ca.uqtr.patient.dto.ErrorDto;
import ca.uqtr.patient.dto.MedicalFileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.UserRequestDto;
import ca.uqtr.patient.dto.medicalfile.AntecedentsDto;
import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.ClinicalExaminationDto;
import ca.uqtr.patient.entity.*;
import ca.uqtr.patient.entity.vo.Contact;
import ca.uqtr.patient.repository.ProfessionalRepository;
import ca.uqtr.patient.repository.medicalFile.MedicalFileRepository;
import ca.uqtr.patient.repository.patient.PatientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private final MedicalFileRepository medicalFileRepository;
    private final ProfessionalRepository professionalRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, MedicalFileRepository medicalFileRepository, ProfessionalRepository professionalRepository) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.medicalFileRepository = medicalFileRepository;
        this.professionalRepository = professionalRepository;
    }

    @Override
    public PatientDto addPatient(PatientDto patientDto, String professionalUsername) {
        PatientDto pDto = new PatientDto();
        try {
                Patient patient = patientDto.dtoToObj(modelMapper);
                Professional professional = professionalRepository.getProfessionalByUsername(professionalUsername);
                if (professional == null){
                    professional = professionalRepository.save(new Professional(professionalUsername, true));
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

                pDto = modelMapper.map(patient_db, PatientDto.class);
        } catch (Exception e){
            pDto.setError(new ErrorDto(1, "Mapping error (check data). Message : "+e.getMessage()));
            return pDto;
        }
        return pDto;
    }

    @Override
    public PatientDto getPatientById(PatientDto patientDto) {
        System.out.println(patientDto.toString());
        System.out.println(patientDto.dtoToObj(modelMapper).getId());
        System.out.println(patientDto.dtoToObj(modelMapper).getFirstName());
        PatientDto pDto = new PatientDto();
        UUID patientId = patientDto.dtoToObj(modelMapper).getId();
        Optional<Patient> patient = patientRepository.findById(patientId);
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileByPatient(patientId.toString());
        MedicalFileDto medicalFileDto = modelMapper.map(medicalFile, MedicalFileDto.class);
        pDto.setMedicalFile(medicalFileDto);
        if (!patient.isPresent()){
            pDto.setError(new ErrorDto(2, "Patient does not exist."));
            return pDto;
        }
        pDto = modelMapper.map(patient.get(), PatientDto.class);
        return pDto;
    }

    @Override
    public Patient getPatientByFirstNameAndLastName(PatientDto patient) {
        return patientRepository.getPatientByFirstNameAndLastName(
                patient.dtoToObj(modelMapper).getFirstName(),
                patient.dtoToObj(modelMapper).getLastName());
    }

    @Override
    public List<Patient> getPatientsByAge(PatientDto patient) {
        return patientRepository.getPatientsByAge(patient.dtoToObj(modelMapper).getBirthday().toString());
    }

    @Override
    public Iterable<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getPatientsByProfessional(String username) {
        return patientRepository.findByProfessionals(professionalRepository.getProfessionalByUsername(username));
    }

    @Override
    public MedicalFileDto addSocioDemographicVariables(String patientId, String socioDemographicVariablesDto) {
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileByPatient(patientId);
        medicalFile.setSocioDemographicVariables(socioDemographicVariablesDto);
        return  modelMapper.map(medicalFileRepository.save(medicalFile), MedicalFileDto.class);
    }

    @Override
    public MedicalFileDto addAntecedents(String patientId, String antecedentsDto) {
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileWith_MedicalFileHistory_FetchTypeEAGER(patientId);
        MedicalFileHistory medicalFileHistory = new MedicalFileHistory(new java.sql.Date(Calendar.getInstance().getTimeInMillis()), antecedentsDto);
        List<MedicalFileHistory> medicalFileHistories = medicalFile.getMedicalFileHistory();
        medicalFileHistories.add(medicalFileHistory);
        medicalFile.setMedicalFileHistory(medicalFileHistories);
        return  modelMapper.map(medicalFileRepository.save(medicalFile), MedicalFileDto.class);
    }

    @Override
    public MedicalFileDto addClinicalExamination(String patientId, ClinicalExaminationDto clinicalExaminationDto) {
        MedicalFile medicalFile = medicalFileRepository.getMedicalFileWith_ClinicalExamination_FetchTypeEAGER(patientId);
        System.out.println(medicalFile.toString());
        List<ClinicalExamination> clinicalExamination = medicalFile.getClinicalExamination();
        clinicalExamination.add(clinicalExaminationDto.dtoToObj(modelMapper));
        medicalFile.setClinicalExamination(clinicalExamination);
        return  modelMapper.map(medicalFileRepository.save(medicalFile), MedicalFileDto.class);
    }

    @Override
    public SocioDemographicVariablesDto getSocioDemographicVariables(PatientDto patient) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String socioDemographicVariables = medicalFileRepository.getMedicalFileByPatient(patient.getId().toString()).getSocioDemographicVariables();
        if (socioDemographicVariables == null)
            return new SocioDemographicVariablesDto();
        return mapper.readValue(
                socioDemographicVariables,
                SocioDemographicVariablesDto.class);
    }

    @Override
    public List<MedicalFileDto> getAntecedents(PatientDto patient) {
        return null;
    }

    @Override
    public List<MedicalFileDto> getClinicalExamination(PatientDto patient) {
        return null;
    }

    @Override
    public List<Patient> getPatientsByProfessional(PatientDto patientDto) {
        /*Patient patient = patientDto.dtoToObj(modelMapper);
        Professional professional = patient.getProfessional();
        System.out.println(professional.toString());
        return patientRepository.getPatientsByProfessional(professional);*/
        return null;
    }

    @Override
    public Patient updatePatient(PatientDto patient) {
        return patientRepository.save(patient.dtoToObj(modelMapper));
    }

    @Override
    public void deleteById(PatientDto patient) {
        patientRepository.deleteById(patient.dtoToObj(modelMapper).getId());
    }

}
