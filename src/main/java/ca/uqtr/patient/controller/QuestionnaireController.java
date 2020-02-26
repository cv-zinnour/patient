package ca.uqtr.patient.controller;

import ca.uqtr.patient.dto.Error;
import ca.uqtr.patient.dto.LipidProfileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.Response;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.service.patient.PatientService;
import ca.uqtr.patient.service.questionnaire.QuestionnaireService;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class QuestionnaireController {

    private final PatientService patientService;
    private final QuestionnaireService questionnaireService;
    private MessageSource messageSource;
    private ModelMapper modelMapper;

    public QuestionnaireController(PatientService patientService, QuestionnaireService questionnaireService, MessageSource messageSource, ModelMapper modelMapper) {
        this.patientService = patientService;
        this.questionnaireService = questionnaireService;
        this.messageSource = messageSource;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/questionnaire/invite")
    public void sendQuestionnaire(@RequestBody PatientDto patientDto) {
        questionnaireService.sendQuestionnaire(patientDto);
    }

    @GetMapping("/questionnaire")
    public Response questionnaireToken(@RequestParam("token") String token)  {

        Patient patient = patientService.getPatientByQuestionnaireToken(token);
        if (patient == null) {
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.questionnaire.token.exist.id", null, Locale.US)),
                            messageSource.getMessage("error.questionnaire.token.exist.message", null, Locale.US)));
        }
        return new Response(modelMapper.map(modelMapper.map(patient, PatientDto.class), PatientDto.class), null);
    }



}
