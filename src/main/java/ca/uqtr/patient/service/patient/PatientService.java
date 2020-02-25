package ca.uqtr.patient.service.patient;

import ca.uqtr.patient.dto.LipidProfileDto;
import ca.uqtr.patient.dto.MedicalFileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.Response;
import ca.uqtr.patient.dto.medicalfile.AntecedentsDto;
import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.ClinicalExaminationDto;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Professional;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface PatientService {

    Response addPatient(PatientDto patientDto, String professionalId);
    Response getPatient(String patientId);
    Response getPatients();
    Response getPatientsByProfessional(String id);
    Response getPatientSocioDemographicVariables(String patientId) throws IOException;
    Response addSocioDemographicVariables(String patientId, String socioDemographicVariablesDto) throws JsonProcessingException;
    Response getPatientAntecedents(String patientId);
    Response addAntecedents(String patientId, String antecedentsDto) throws JsonProcessingException;
    Response getPatientClinicalExaminationList(String patientId);
    Response addClinicalExamination(String patientId, ClinicalExaminationDto clinicalExaminationDto);
    Response updatePatient(PatientDto patient);

    Response getPatientLipidProfile(String patientId);
    Response addLipidProfile(String patientId, LipidProfileDto lipidProfileDto);

    void createQuestionnaireToken(String patientId, String token);

}
