package ca.uqtr.patient.event.questionnaire;

import ca.uqtr.patient.dto.PatientDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnQuestionnaireSendEvent extends ApplicationEvent {
    private PatientDto patient;

    public OnQuestionnaireSendEvent(PatientDto patient) {
        super(patient);
        this.patient = patient;
    }

}
