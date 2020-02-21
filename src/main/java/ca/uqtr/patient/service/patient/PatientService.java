package ca.uqtr.patient.service.patient;

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

    Response addPatient(PatientDto patientDto, String professionalUsername);
    PatientDto getPatientById(PatientDto patientDto);
    Iterable<Patient> getPatients();
    List<Patient> getPatientsByProfessional(PatientDto patientDto);
    Patient getPatientByFirstNameAndLastName(PatientDto patient);
    List<Patient> getPatientsByAge(PatientDto patient);
    Patient updatePatient(PatientDto patient);
    void deleteById(PatientDto patient);
    List<Patient> getPatientsByProfessional(String username);
    MedicalFileDto addSocioDemographicVariables(String patientId, String socioDemographicVariablesDto) throws JsonProcessingException;
    MedicalFileDto addAntecedents(String patientId, String antecedentsDto) throws JsonProcessingException;
    MedicalFileDto addClinicalExamination(String patientId, ClinicalExaminationDto clinicalExaminationDto);

    SocioDemographicVariablesDto getSocioDemographicVariables(PatientDto patient) throws IOException;
    List<MedicalFileDto> getAntecedents(PatientDto patient);
    List<MedicalFileDto> getClinicalExamination(PatientDto patient);
}
