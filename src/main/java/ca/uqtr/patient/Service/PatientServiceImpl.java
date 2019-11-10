package ca.uqtr.patient.Service;


import ca.uqtr.patient.DTO.PatientDTO;
import ca.uqtr.patient.Entity.Patient;
import ca.uqtr.patient.Repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {


    PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteById(UUID id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient newPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient convertPatientToDTO(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);



        return patient;
    }


}
