package ca.uqtr.patient.service.appointment;


import ca.uqtr.patient.dto.AppointmentDto;
import ca.uqtr.patient.dto.Response;

public interface AppointmentService {
    Response addAppointment(AppointmentDto appointmentDto);

    Response getAppointments(String professionalId);

    Response updateAppointment(AppointmentDto appointmentDto);

    Response deleteAppointment(AppointmentDto appointmentDto);

}
