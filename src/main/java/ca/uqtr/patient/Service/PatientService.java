package ca.uqtr.patient.Service;

import ca.uqtr.patient.DTO.PatientDTO;
import ca.uqtr.patient.Entity.Patient;

import java.util.UUID;

public interface PatientService {

    void deleteById(UUID id);
    Patient updatePatient(Patient patient);
    Patient newPatient(Patient patient);
    Patient convertPatientToDTO(PatientDTO patient);
}
