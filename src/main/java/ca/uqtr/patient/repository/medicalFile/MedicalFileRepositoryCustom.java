package ca.uqtr.patient.repository.medicalFile;

import ca.uqtr.patient.entity.MedicalFile;

public interface MedicalFileRepositoryCustom {

    MedicalFile getMedicalFileByPatient(String patientId);
}
