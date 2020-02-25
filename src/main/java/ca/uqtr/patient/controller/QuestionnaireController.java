package ca.uqtr.patient.controller;

import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.Response;
import ca.uqtr.patient.service.patient.PatientService;
import ca.uqtr.patient.service.questionnaire.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@RestController
public class QuestionnaireController {

    private final PatientService patientService;
    private final QuestionnaireService questionnaireService;

    public QuestionnaireController(PatientService patientService, QuestionnaireService questionnaireService) {
        this.patientService = patientService;
        this.questionnaireService = questionnaireService;
    }

    @PostMapping(value = "/questionnaire/invite")
    public void sendQuestionnaire(@RequestBody PatientDto patientDto) {
        questionnaireService.sendQuestionnaire(patientDto);
    }



    @GetMapping("/questionnaire")
    public Response userInviteToken(@RequestParam("token") String token)  {
       return null;
    }

}
