package ca.uqtr.patient.Repository;

import ca.uqtr.patient.DTO.PatientDTO;
import ca.uqtr.patient.Entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends CrudRepository<Patient, UUID> {

}
