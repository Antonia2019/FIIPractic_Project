package fii.practic.health.control.service;

import fii.practic.health.entity.model.Appointment;

import java.util.Map;

public interface AppointmentService {

    Map<String, String> getAllSpecialization();

    void saveAppointment(Appointment appointment);

    void rescheduleAppointment(Appointment appointment);

    Appointment getAppointment(Integer id);

    void cancelAppointment(Integer id);

    void acceptAppointment(Integer id);

    void rejectAppointment(Integer id);

    void updateAppointment(Appointment appointment);
}
