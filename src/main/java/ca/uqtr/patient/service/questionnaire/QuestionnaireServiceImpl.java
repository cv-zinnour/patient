package ca.uqtr.patient.service.questionnaire;

import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.QuestionnaireDto;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Questionnaire;
import ca.uqtr.patient.event.questionnaire.OnQuestionnaireSendEvent;
import ca.uqtr.patient.repository.patient.PatientRepository;
import ca.uqtr.patient.repository.questionnaire.QuestionnaireRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository, PatientRepository patientRepository, ModelMapper modelMapper, ApplicationEventPublisher eventPublisher) {
        this.questionnaireRepository = questionnaireRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public QuestionnaireDto addQuestionnaire(String patientId, QuestionnaireDto questionnaireDto) {
        Patient patient = patientRepository.getPatientById(UUID.fromString(patientId));
        Questionnaire questionnaire = questionnaireDto.dtoToObj(modelMapper);
        List<Questionnaire> questionnaires = patient.getQuestionnaires();
        questionnaires.add(questionnaire);
        patientRepository.save(patient);
        return questionnaireDto;
    }

    @Override
    public List<Questionnaire> getQuestionnaires(String patientId) {
        Patient patient = patientRepository.getPatientById(UUID.fromString(patientId));
        List<Questionnaire> questionnaires = patient.getQuestionnaires();
       // if (questionnaires == null || questionnaires.isEmpty())

        return questionnaires;
    }

    @Override
    public void sendQuestionnaire(PatientDto patientDto) {
        eventPublisher.publishEvent(new OnQuestionnaireSendEvent(patientDto, 1));
    }



}
