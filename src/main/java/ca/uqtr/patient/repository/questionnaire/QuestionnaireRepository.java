package ca.uqtr.patient.repository.questionnaire;

import ca.uqtr.patient.entity.Professional;
import ca.uqtr.patient.entity.Questionnaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, UUID> {

}
