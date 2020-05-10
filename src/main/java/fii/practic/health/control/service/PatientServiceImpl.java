package fii.practic.health.control.service;

import fii.practic.health.boundry.util.DoctorUtil;
import fii.practic.health.entity.model.Appointment;
import fii.practic.health.entity.model.Authority;
import fii.practic.health.entity.model.AuthorityRole;
import fii.practic.health.entity.model.Patient;
import fii.practic.health.entity.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient save(Patient patient) {
        Authority authority = new Authority();
        authority.setAuthorityRole(AuthorityRole.ROLE_PATIENT);
        patient.getUser().getAuthorities().add(authority);

        String encodedPassword = DoctorUtil.hashPassword(patient.getUser().getPassword());
        patient.getUser().setPassword(encodedPassword);

        return  patientRepository.save(patient);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient patch(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    @Override
    public Patient findPatientByEmail(String email) {
        return patientRepository.findPatientByEmail(email);
    }

    @Override
    public Map<Date, List<Appointment>> getUpcomingAppointment(List<Appointment> appointmentList) {
        return DoctorUtil.mapAppointmentFromList(appointmentList, false);
    }

    @Override
    public Map<Date, List<Appointment>> getOverdueAppointment(List<Appointment> appointmentList) {
        return DoctorUtil.mapAppointmentFromList(appointmentList, true);
    }
}
