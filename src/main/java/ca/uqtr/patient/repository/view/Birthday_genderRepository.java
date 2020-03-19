package ca.uqtr.patient.repository.view;

import ca.uqtr.patient.entity.view.Birthday_gender;
import ca.uqtr.patient.entity.view.Height_weight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Birthday_genderRepository extends CrudRepository<Birthday_gender, UUID> {

}
