package fii.practic.health.control.service;

import fii.practic.health.entity.model.Appointment;
import fii.practic.health.entity.model.Patient;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PatientService {

    Patient getById(Long id);

    Patient save(Patient patient);

    Patient update(Patient patient);

    Patient patch(Patient patient);

    void delete(Patient patient);

    Map<Date, List<Appointment>> getUpcomingAppointment(List<Appointment> appointmentList);

    Map<Date, List<Appointment>> getOverdueAppointment(List<Appointment> appointmentList);

    Patient findPatientByEmail(String name);
}
