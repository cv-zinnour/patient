package ca.uqtr.patient.repository.patient;

import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.view.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, UUID> {
}
