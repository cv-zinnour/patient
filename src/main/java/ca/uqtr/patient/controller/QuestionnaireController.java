package ca.uqtr.patient.controller;

import ca.uqtr.patient.dto.*;
import ca.uqtr.patient.dto.Error;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.service.patient.PatientService;
import ca.uqtr.patient.service.questionnaire.QuestionnaireService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@RestController
public class QuestionnaireController {

    private final PatientService patientService;
    private final QuestionnaireService questionnaireService;
    private MessageSource messageSource;
    private ModelMapper modelMapper;
    private ObjectMapper mapper;

    public QuestionnaireController(PatientService patientService, QuestionnaireService questionnaireService, MessageSource messageSource, ModelMapper modelMapper, ObjectMapper mapper) {
        this.patientService = patientService;
        this.questionnaireService = questionnaireService;
        this.messageSource = messageSource;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
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

    @PostMapping(value = "/questionnaire")
    @ResponseBody
    public Response addPatient(@RequestBody Request request, HttpServletRequest HttpRequest) throws IOException {
        String token = HttpRequest.getHeader("Authorization").replace("bearer ","");
        QuestionnaireDto questionnaireDto = mapper.convertValue(request.getObject(), QuestionnaireDto.class);
        return questionnaireService.addQuestionnaire(questionnaireDto);
    }

}
