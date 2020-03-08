package ca.uqtr.patient.schedule;

import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.event.questionnaire.OnQuestionnaireSendEvent;
import ca.uqtr.patient.repository.AppointmentRepository;
import ca.uqtr.patient.repository.patient.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledNotification {

    private PatientRepository patientRepository;
    private ApplicationEventPublisher eventPublisher;
    private ModelMapper modelMapper;

    public ScheduledNotification(PatientRepository patientRepository, ApplicationEventPublisher eventPublisher, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.eventPublisher = eventPublisher;
        this.modelMapper = modelMapper;
    }

    /*@Bean
    public int isTherePatientDoesntSync()
    {
        return cronRepo.findOne("cron").getCronValue();
    }, cron="#{@getCronValue}"*/

    @Scheduled(fixedDelay = 43200)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        Calendar cal = Calendar.getInstance();
        List<Patient> patients = patientRepository.patientsWithAppointmentAfterADay(new Date(cal.getTime().getTime() + TimeUnit.DAYS.toMillis(1)));
        if (patients != null && !patients.isEmpty()){
            for (Patient patient: patients) {
                if (patient.getAppointments().get(0).isJpaqEmail()){
                    patient.getAppointments().get(0).setJpaqEmail(true);
                    patientRepository.save(patient);
                    eventPublisher.publishEvent(new OnQuestionnaireSendEvent(modelMapper.map(patient, PatientDto.class), 2));

                }
            }
        }
    }
}
