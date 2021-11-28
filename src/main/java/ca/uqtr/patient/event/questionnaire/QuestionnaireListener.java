package ca.uqtr.patient.event.questionnaire;

import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.service.patient.PatientService;
import ca.uqtr.patient.service.questionnaire.QuestionnaireService;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class QuestionnaireListener implements
        ApplicationListener<OnQuestionnaireSendEvent> {

    @Value("${spring.profiles.active}")
    private String mailService;
    private final JavaMailSender mailSender;
    private PatientService patientService;
    @Value("${patient-service.questionnaire.url}")
    private String QUESTIONNAIRE_URL;

    @Autowired
    public QuestionnaireListener(JavaMailSender mailSender, PatientService patientService) {
        this.mailSender = mailSender;
        this.patientService = patientService;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(OnQuestionnaireSendEvent event) {
        if (mailService.equals("dev"))
            this.confirmQuestionnaireSendGmail(event);
        else
            this.confirmQuestionnaireSendGrid(event);
        /*this.confirmRegistrationSendGrid(event);*/
    }

    private void confirmQuestionnaireSendGrid(OnQuestionnaireSendEvent event) throws IOException {
        String templateId;
        PatientDto patient = event.getPatient();
        int rdv = event.getRdv();
        String token = UUID.randomUUID().toString();
        patientService.createQuestionnaireToken(patient.getId().toString(), token);
        String recipientAddress = patient.getContact().getEmail();
        /*@Value("${mail.uri}")*/
        //String URI_HEROKU = "https://epod-zuul.herokuapp.com/api/v1/auth-service/update/password?token=";
        String URI_HEROKU = "http://localhost:4200/patient/questionnaire?token=";
        String confirmationUrl = QUESTIONNAIRE_URL + token;
        String subject;
        if (rdv == 1){
            subject = "I-POD Sante - Personal informations and BREQ questionnaire";
            templateId = "d-6f13164e0ce04d9ebbec746cd112c147";
        }
        else{
            subject = "I-POD Sante - GPAQ questionnaire and recommendations";
            templateId = "d-308639d57bf945fa912e20b5a367db02";
        }
        System.out.println(subject);

        Mail mail = new Mail();
        mail.setFrom(new Email("sadegh.moulaye.abdallah@uqtr.ca", "I-POD SANTE"));
        mail.setSubject(subject);
        mail.setTemplateId(templateId);
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("name", patient.getFirstName());
        personalization.addDynamicTemplateData("email", patient.getContact().getEmail());
        personalization.addDynamicTemplateData("pin", patient.getLoginCode());
        personalization.addDynamicTemplateData("link", confirmationUrl);
        personalization.addTo(new Email(recipientAddress));
        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        System.out.println(request.getBody());
        Response response = sg.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
    }

    private void confirmQuestionnaireSendGmail(OnQuestionnaireSendEvent event) {
        PatientDto patient = event.getPatient();
        int rdv = event.getRdv();
        String token = UUID.randomUUID().toString();
        patientService.createQuestionnaireToken(patient.getId().toString(), token);
        assert patient.getContact() != null;
        String recipientAddress = patient.getContact().getEmail();
        String subject;
        if (rdv == 1)
            subject = "POD iSante - Informations personal and BREQ questionnaire!";
        else
            subject = "POD iSante - JPAQ questionnaire and recommendations!";
        String URI_GMAIL = "http://localhost:8762/api/v1/patient-service/questionnaire?token=";
        String confirmationUrl
                = QUESTIONNAIRE_URL + token;
        String message = "PIN : "+patient.getQuestionnaireToken()+". To fill your questionnaire click here : ";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message+confirmationUrl);
        mailSender.send(email);
    }

}


