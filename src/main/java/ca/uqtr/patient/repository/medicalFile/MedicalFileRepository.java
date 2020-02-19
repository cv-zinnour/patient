package ca.uqtr.patient.repository.medicalFile;

import ca.uqtr.patient.entity.MedicalFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MedicalFileRepository extends CrudRepository<MedicalFile, UUID>, MedicalFileRepositoryCustom {

    MedicalFile getMedicalFileByPatient(String id);
    void deleteByPatient(String patientId);

    @Query("SELECT p FROM Patient p WHERE p.birthday = :date")
    MedicalFile getMedicalFileWith_ClinicalExamination_FetchTypeEAGER(String id);

    @Query("SELECT p FROM Patient p WHERE p.birthday = :date")
    MedicalFile getMedicalFileWith_MedicalFileHistory_FetchTypeEAGER(String id);

}
