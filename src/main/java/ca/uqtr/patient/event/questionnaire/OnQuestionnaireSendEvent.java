package ca.uqtr.patient.event.questionnaire;

import ca.uqtr.patient.dto.PatientDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnQuestionnaireSendEvent extends ApplicationEvent {
    private PatientDto patient;
    private int rdv;

    public OnQuestionnaireSendEvent(PatientDto patient, int rdv) {
        super(patient);
        this.patient = patient;
        this.rdv = rdv;
    }

}
