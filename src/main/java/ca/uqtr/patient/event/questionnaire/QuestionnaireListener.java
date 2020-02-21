package ca.uqtr.patient.event.questionnaire;

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

    @Autowired
    public QuestionnaireListener( JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(OnQuestionnaireSendEvent event) {
        if (mailService.equals("dev"))
            this.confirmRegistrationGmail(event);
        else
            this.confirmRegistrationSendGrid(event);
        /*this.confirmRegistrationSendGrid(event);*/

    }

    private void confirmRegistrationSendGrid(OnQuestionnaireSendEvent event) {

    }


    private void confirmRegistrationGmail(OnQuestionnaireSendEvent event) {

    }

}


