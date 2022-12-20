package ca.uqtr.patient.repository.clinicalExamination;

import ca.uqtr.patient.entity.ClinicalExamination;
import ca.uqtr.patient.entity.MedicalFile;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClinicalExaminationRepository extends CrudRepository<ClinicalExamination, UUID> {
    ClinicalExamination getClinicalExaminationById(String id);


}
