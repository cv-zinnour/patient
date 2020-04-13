package ca.uqtr.patient.repository.recommendation;

import ca.uqtr.patient.entity.Appointment;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Recommendation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecommendationRepository  extends CrudRepository<Recommendation, Integer> {
    @Query("SELECT r FROM Recommendation r WHERE r.patient = :patient AND r.response IS NULL ORDER BY r.id DESC")
    List<Recommendation> getRecommendationByPatientAndResponseIsNull(Patient patient);
}
