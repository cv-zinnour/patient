package ca.uqtr.patient.repository.patient;

import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Professional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientRepository extends CrudRepository<Patient, UUID> {
    Patient getPatientById(UUID id);
    Patient getPatientByFirstNameAndLastName(String firstname, String lastname);
    @Query("SELECT p FROM Patient p WHERE p.birthday = :date")
    List<Patient> getPatientsByAge(@Param("date")String date);

    List<Patient> findByProfessionals(Professional professional);

    Patient getPatientByQuestionnaireToken(String token);

    Patient getPatientByContact_Email(String email);

    @Query("select p from Patient p left join fetch  p.questionnaires q where q.patient.id = :patientId AND q.type = 'BREQ'")
    Patient isPatientDidAnswerBREQ(UUID patientId);

//
//
//    @Query("select pt from Patient pt join Professional pr where pr.username = :username")
//    List<Patient> findByProfessionalsUsername(@Param("username")String username);
}
