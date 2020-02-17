package ca.uqtr.patient.service.patient;


import ca.uqtr.patient.dto.ErrorDto;
import ca.uqtr.patient.dto.MedicalFileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Professional;
import ca.uqtr.patient.repository.medicalFile.MedicalFileRepository;
import ca.uqtr.patient.repository.patient.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private final MedicalFileRepository medicalFileRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, MedicalFileRepository medicalFileRepository) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.medicalFileRepository = medicalFileRepository;
    }

    @Override
    public PatientDto addPatient(PatientDto patientDto) {
        System.out.println(patientDto);
        PatientDto pDto = new PatientDto();
        try {
            Patient patient = patientDto.dtoToObj(modelMapper);
            Patient p = patientRepository.save(patient);

            MedicalFile medicalFile = new MedicalFile();
            medicalFile.setPatient(p.getId().toString());
            medicalFileRepository.save(medicalFile);

            //Professional professional = patient.getProfessionals().iterator().next();

            pDto = modelMapper.map(p, PatientDto.class);
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
    public List<Patient> getPatientsByProfessional(PatientDto patientDto) {
        Patient patient = patientDto.dtoToObj(modelMapper);
        Professional professional = patient.getProfessionals().iterator().next();
        System.out.println(professional.toString());
        return patientRepository.getPatientsByProfessionals_Id(professional);
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
