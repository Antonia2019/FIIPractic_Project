package fii.practic.health.control.service;

import fii.practic.health.entity.model.Appointment;
import fii.practic.health.entity.model.Doctor;
import fii.practic.health.entity.model.Specialization;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DoctorService {

    List<Doctor> getAll();

    Doctor save(Doctor doctor);

    Doctor findDoctorByEmail(String name);

    Doctor findDoctorById(long doctorId);

    List<Doctor> findDoctorsByPatientsFirstName(String firstName);

    void update(Doctor doctor);

    Doctor patch(Doctor doctor);

    void delete(Doctor doctor);

    Map<Long, String> findDoctorBySpecialization(Specialization spec);

    Map<Date, List<Appointment>> getUpcomingAppointment(List<Appointment> appointmentList);

    Map<Date, List<Appointment>> getOverdueAppointment(List<Appointment> appointmentList);

}
