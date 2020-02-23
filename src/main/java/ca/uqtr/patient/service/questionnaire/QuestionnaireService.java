package ca.uqtr.patient.service.questionnaire;

import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.QuestionnaireDto;
import ca.uqtr.patient.entity.Questionnaire;

import java.util.List;

public interface QuestionnaireService {


    QuestionnaireDto addQuestionnaire(String patientId, QuestionnaireDto questionnaireDto);
    List<Questionnaire> getQuestionnaires(String patientId);
    void sendQuestionnaire(PatientDto patientDto);

}