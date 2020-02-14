package ca.uqtr.patient.repository.medicalFile;

import ca.uqtr.patient.entity.MedicalFile;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MedicalFileRepository extends CrudRepository<MedicalFile, UUID>, MedicalFileRepositoryCustom {

    MedicalFile getMedicalFileByPatient(String id);
    void deleteByPatient(String patientId);


}
