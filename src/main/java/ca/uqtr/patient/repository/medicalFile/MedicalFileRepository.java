package ca.uqtr.patient.repository.medicalFile;

import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MedicalFileRepository extends CrudRepository<MedicalFile, UUID>, MedicalFileRepositoryCustom {

    MedicalFile getMedicalFileByPatient(String id);
    void deleteByPatient(String patientId);

    @Query("select mf from MedicalFile mf left join fetch mf.clinicalExamination where mf.patient = :id")
    MedicalFile  getMedicalFileWith_ClinicalExamination_FetchTypeEAGER(String id);

    @Query("select mf from MedicalFile mf left join fetch mf.medicalFileHistory where mf.patient = :id")
    MedicalFile getMedicalFileWith_MedicalFileHistory_FetchTypeEAGER(String id);

}
