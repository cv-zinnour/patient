package ca.uqtr.patient.Repository;

import ca.uqtr.patient.Entity.MedicalFile;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MedicalFileRepository extends CrudRepository<MedicalFile, UUID> {
}
