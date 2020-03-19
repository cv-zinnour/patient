package ca.uqtr.patient.repository.view;

import ca.uqtr.patient.entity.Appointment;
import ca.uqtr.patient.entity.view.Height_weight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Height_weightRepository extends CrudRepository<Height_weight, UUID> {

    Height_weight getByPatientId(String patientId);
}
