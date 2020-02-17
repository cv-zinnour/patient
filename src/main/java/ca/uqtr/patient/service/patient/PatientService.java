package ca.uqtr.patient.service.patient;

import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.Patient;

import java.util.List;

public interface PatientService {

    PatientDto addPatient(PatientDto patientDto);
    PatientDto getPatientById(PatientDto patientDto);
    Iterable<Patient> getPatients();
    List<Patient> getPatientsByProfessional(PatientDto patientDto);
    Patient getPatientByFirstNameAndLastName(PatientDto patient);
    List<Patient> getPatientsByAge(PatientDto patient);
    Patient updatePatient(PatientDto patient);
    void deleteById(PatientDto patient);

}
