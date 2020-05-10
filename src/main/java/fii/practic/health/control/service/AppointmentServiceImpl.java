package fii.practic.health.control.service;

import fii.practic.health.boundry.util.DoctorUtil;
import fii.practic.health.entity.model.*;
import fii.practic.health.entity.repository.AppointmentRepository;
import fii.practic.health.entity.repository.DoctorRepository;
import fii.practic.health.entity.repository.PatientRepository;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;

    private PatientRepository patientRepository;

    private DoctorRepository doctorRepository;




    @Override
    public Map<String, String> getAllSpecialization() {

        Map<String, String> specializations = new HashMap<>();
        for (Specialization specialization : Specialization.values()) {
            specializations.put(specialization.toString(), WordUtils.capitalizeFully(specialization.toString()));
        }
        return specializations;
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        Patient patient = patientRepository.findPatientByEmail(DoctorUtil.getEmail());
        Doctor doctor = doctorRepository.findOne(appointment.getDoctor().getDoctorId());

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(Integer id) {
        return appointmentRepository.findOne(id);
    }

    @Override
    public void rescheduleAppointment(Appointment appointment) {
        Appointment appToBeUpdated = appointmentRepository.findOne(appointment.getAppointmentId());
        appToBeUpdated.setDate(appointment.getDate());
        appToBeUpdated.setStatus(AppointmentStatus.NEW);

        appointmentRepository.save(appToBeUpdated);
    }

    @Override
    public void cancelAppointment(Integer id) {
        Appointment appointment = appointmentRepository.findOne(id);
        this.updateAppointmentStatus(appointment, AppointmentStatus.CANCELED);
    }

    @Override
    public void acceptAppointment(Integer id) {
        Appointment appointment = appointmentRepository.findOne(id);
        this.updateAppointmentStatus(appointment, AppointmentStatus.ACCEPTED);

    }

    @Override
    public void rejectAppointment(Integer id) {
        Appointment appointment = appointmentRepository.findOne(id);
        this.updateAppointmentStatus(appointment, AppointmentStatus.REJECTED);
    }

    private void updateAppointmentStatus(Appointment appointment, AppointmentStatus status) {
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        Appointment appointmentToBeUpdated = appointmentRepository.findOne(appointment.getAppointmentId());
        appointmentToBeUpdated.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointmentToBeUpdated);
    }
}