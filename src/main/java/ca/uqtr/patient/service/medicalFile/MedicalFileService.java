package ca.uqtr.patient.service.medicalFile;

import ca.uqtr.patient.dto.MedicalFileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface MedicalFileService {
    MedicalFile getMedicalFileByPatient(PatientDto patient);
    MedicalFile newMedicalFile(MedicalFileDto medicalFile);
    MedicalFile updateMedicalFile(MedicalFileDto medicalFile);
    void deleteMedicalFileById(MedicalFileDto medicalFile);
}
