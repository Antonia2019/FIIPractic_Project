package fii.practic.health.control.service;

import fii.practic.health.boundry.util.DoctorUtil;
import fii.practic.health.entity.model.*;
import fii.practic.health.entity.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<Doctor>();

        for (Doctor d : doctorRepository.findAll()) {
            doctors.add(d);
        }
        return doctors;
    }

    @Override
    public Doctor save(Doctor doctor) {
        Authority authority = new Authority();
        authority.setAuthorityRole(AuthorityRole.ROLE_DOCTOR);
        doctor.getUser().getAuthorities().add(authority);
        String encodedPassword = DoctorUtil.hashPassword(doctor.getUser().getPassword());
        doctor.getUser().setPassword(encodedPassword);
        return doctorRepository.save(doctor);
    }

    @Override //*
    public Doctor findDoctorById(long id) {
        Doctor doctor = doctorRepository.findDoctorById(id);
        return doctor;
    }

    @Override //*
    public List<Doctor> findDoctorsByPatientsFirstName(String firstName) {
        return doctorRepository.findDoctorsByPatientsFirstName(firstName);
    }

    @Override
    public void update(Doctor doctor) {
        Doctor doctorUpdate = doctorRepository.findDoctorById(doctor.getDoctorId());
        if (!doctor.getUser().getPassword().isEmpty()) {
            String encodedPassword = DoctorUtil.hashPassword(doctor.getUser().getPassword());
            doctorUpdate.getUser().setPassword(encodedPassword);
        }
        doctorRepository.save(doctorUpdate);
    }

    @Override //*
    public Doctor patch(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override //*
    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    @Override
    public Map<Long, String> findDoctorBySpecialization(Specialization spec) {
        List<Doctor> doctors = doctorRepository.findDoctorBySpecialization(spec);

        if (doctors.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Long, String> doctorMap = new HashMap<>();
        for (Doctor doctor : doctors) {
            doctorMap.put(doctor.getDoctorId(), doctor.getFullName());
        }
        return doctorMap;
    }

    @Override
    public Doctor findDoctorByEmail(String email) {
        return doctorRepository.findDoctorByEmail(email);
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
