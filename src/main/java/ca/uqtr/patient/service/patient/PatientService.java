package ca.uqtr.patient.service.patient;

import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Patient newPatient(PatientDto patient);
    Optional<Patient> getPatientById(PatientDto patient);
    Iterable<Patient> getPatients();
    Patient getPatientByFirstNameAndLastName(PatientDto patient);
    List<Patient> getPatientsByAge(PatientDto patient);
    Patient updatePatient(PatientDto patient);
    void deleteById(PatientDto patient);

}
