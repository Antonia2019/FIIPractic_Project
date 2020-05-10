package fii.practic.health.boundry.controller;

import fii.practic.health.entity.model.Appointment;
import fii.practic.health.entity.model.Doctor;
import fii.practic.health.control.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/doctors")
@CrossOrigin
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/doctors/home", method = RequestMethod.GET)
    public String showPatientHome(@ModelAttribute("doctor") Doctor doctor, Model model, Principal principal) {
        Doctor foundDoctor = doctorService.findDoctorByEmail(principal.getName());
        Map<Date, List<Appointment>> upcomingAppointment = doctorService
                .getUpcomingAppointment(foundDoctor.getAppointmentList());
        Map<Date, List<Appointment>> overdueAppointment = doctorService
                .getOverdueAppointment(foundDoctor.getAppointmentList());

        model.addAttribute("upcomingAppointment", upcomingAppointment);
        model.addAttribute("overdueAppointment", overdueAppointment);
        model.addAttribute("doctor", foundDoctor);
        return "doctor-home";
    }
}
