package ca.uqtr.patient.service.patient;


import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.repository.patient.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Patient newPatient(PatientDto patient) {
        return patientRepository.save(patient.patient2Dto(modelMapper));
    }

    @Override
    public Optional<Patient> getPatientById(PatientDto patient) {
        return patientRepository.findById(patient.patient2Dto(modelMapper).getId());
    }

    @Override
    public Patient getPatientByFirstNameAndLastName(PatientDto patient) {
        return patientRepository.getPatientByFirstNameAndLastName(
                patient.patient2Dto(modelMapper).getFirstName(),
                patient.patient2Dto(modelMapper).getLastName());
    }

    @Override
    public List<Patient> getPatientsByAge(PatientDto patient) {
        return patientRepository.getPatientsByAge(patient.patient2Dto(modelMapper).getBirthday().toString());
    }

    @Override
    public Iterable<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(PatientDto patient) {
        return patientRepository.save(patient.patient2Dto(modelMapper));
    }

    @Override
    public void deleteById(PatientDto patient) {
        patientRepository.deleteById(patient.patient2Dto(modelMapper).getId());
    }



}
