package ca.uqtr.patient.repository.medicalFile;

import ca.uqtr.patient.entity.MedicalFile;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.UUID;


@Repository
@Transactional(readOnly = true)
public class MedicalFileRepositoryCustomImpl implements MedicalFileRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public MedicalFile getMedicalFileByPatient(String patient) {
        Query query = entityManager.createNativeQuery("SELECT * FROM medical_file as mf " +
                "WHERE mf.patient = ?", MedicalFile.class);
        query.setParameter(1, patient);
        return (MedicalFile) query.getSingleResult();
    }

    public String getPatient(String patient) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword("pod-uqtr");
        return encryptor.decrypt(patient);
    }

}
