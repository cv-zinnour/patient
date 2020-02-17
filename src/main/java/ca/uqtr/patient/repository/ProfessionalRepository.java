package ca.uqtr.patient.repository;

import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Professional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfessionalRepository extends CrudRepository<Professional, UUID> {

}
