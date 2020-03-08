package ca.uqtr.patient.service.appointment;

import ca.uqtr.patient.dto.AppointmentDto;
import ca.uqtr.patient.dto.Response;
import ca.uqtr.patient.dto.Error;
import ca.uqtr.patient.entity.Appointment;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.repository.AppointmentRepository;
import ca.uqtr.patient.repository.patient.PatientRepository;
import javassist.bytecode.stackmap.TypeData;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

    private AppointmentRepository appointmentRepository;
    private MessageSource messageSource;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, MessageSource messageSource, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.messageSource = messageSource;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response addAppointment(AppointmentDto appointmentDto){
        Optional<Patient> patient = patientRepository.findById(appointmentDto.getPatientId());
        if (!patient.isPresent())
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.user.id", null, Locale.US)),
                            messageSource.getMessage("error.user.message", null, Locale.US)));
        try{
            Type appointmentDtoList = new TypeToken<List<AppointmentDto>>() {}.getType();
            List<Appointment> appointments = patient.get().getAppointments();
            appointments.add(appointmentDto.dtoToObj(modelMapper));
            patientRepository.save(patient.get());
            return new Response(modelMapper.map(appointments, appointmentDtoList), null);
        } catch (Exception ex){
            LOGGER.log( Level.ALL, ex.getMessage());
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.null.id", null, Locale.US)),
                            messageSource.getMessage("error.null.message", null, Locale.US)));
        }

    }

    @Override
    public Response getAppointments(String patientId) {
        Optional<Patient> patient = patientRepository.findById(UUID.fromString(patientId));
        if (!patient.isPresent())
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.user.id", null, Locale.US)),
                            messageSource.getMessage("error.user.message", null, Locale.US)));
        try{
            Type appointmentDtoList = new TypeToken<List<AppointmentDto>>() {}.getType();
            List<Appointment> appointments = patient.get().getAppointments();
            return new Response(modelMapper.map(appointments, appointmentDtoList), null);
        } catch (Exception ex){
            LOGGER.log( Level.ALL, ex.getMessage());
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.null.id", null, Locale.US)),
                            messageSource.getMessage("error.null.message", null, Locale.US)));
        }
    }

    @Override
    public Response updateAppointment(AppointmentDto appointmentDto){
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentDto.getId());
        if (!appointment.isPresent())
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.appointment.id", null, Locale.US)),
                            messageSource.getMessage("error.appointment.message", null, Locale.US)));
        try{
            appointmentDto.setId(appointment.get().getId().toString());
            return new Response(modelMapper.map(appointmentRepository.save(appointmentDto.dtoToObj(modelMapper)), AppointmentDto.class), null);
        } catch (Exception ex){
            LOGGER.log( Level.ALL, ex.getMessage());
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.null.id", null, Locale.US)),
                            messageSource.getMessage("error.null.message", null, Locale.US)));
        }
    }

    @Override
    public Response deleteAppointment(AppointmentDto appointmentDto){
        try{
            appointmentRepository.deleteById(appointmentDto.getId());
            return new Response(appointmentDto, null);
        } catch (Exception ex){
            LOGGER.log( Level.ALL, ex.getMessage());
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.null.id", null, Locale.US)),
                            messageSource.getMessage("error.null.message", null, Locale.US)));
        }

    }
}
