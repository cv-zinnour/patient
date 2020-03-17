package ca.uqtr.patient.repository.recommendation;

import ca.uqtr.patient.entity.Appointment;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Recommendation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecommendationRepository  extends CrudRepository<Recommendation, Integer> {

    Recommendation getRecommendationByPatientAndResponseIsNotNull(Patient patient);
}
