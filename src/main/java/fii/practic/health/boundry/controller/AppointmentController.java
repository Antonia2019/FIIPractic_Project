package fii.practic.health.boundry.controller;

import fii.practic.health.control.service.AppointmentService;
import fii.practic.health.control.service.DoctorService;
import fii.practic.health.entity.model.Appointment;
import fii.practic.health.entity.model.AuthorityRole;
import fii.practic.health.entity.model.Specialization;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/appointments/register", method = RequestMethod.GET)
    public String showRegistrationAppointment(@ModelAttribute("appointment")Appointment appointment, Model model){
        initData(appointment, model);
        return "appointment-creation";
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.POST)
    public String showRegistrationAppointment(@Valid @ModelAttribute("appointment") Appointment appointment,
                                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            initData(appointment, model);
            return "appointment-creation";
        }

        appointmentService.saveAppointment(appointment);

        return "redirect:/patients/home";
    }

    private void initData(Appointment appointment, Model model) {
        appointment.getDoctor().setSpeciality(null);
        model.addAttribute("specialization",appointmentService.getAllSpecialization());
    }

    @RequestMapping(value = "/specializations/{spec}", method = RequestMethod.GET, consumes = "application/json", produces = {
            "application/json" })
    public @ResponseBody
    Map<Long, String> findDoctorBySpecialization(@PathVariable("spec") Specialization spec) {
        Map<Long, String> doctorMap = doctorService.findDoctorBySpecialization(spec);
        return doctorMap;
    }

    @RequestMapping(value = "/appointments/{id}/reschedule", method = RequestMethod.GET)
    public String showReschedulingAppointment(@ModelAttribute("appointment") Appointment appointment,
                                              @PathVariable("id") Integer appointmentId, Model model) {
        appointment = appointmentService.getAppointment(appointmentId);
        model.addAttribute("appointment", appointment);
        return "appointment-reschedule";
    }

    @RequestMapping(value = "/appointments/{id}/reschedule", method = RequestMethod.POST)
    public String rescheduleAppointment(@Valid @ModelAttribute("appointment") Appointment appointment,
                                        BindingResult bindingResult, @PathVariable("id") Integer id, Model model) {

        if (bindingResult.hasErrors()) {
            return "appointment-reschedule";
        }

        appointment.setAppointmentId(id);
        appointmentService.rescheduleAppointment(appointment);
        return "redirect:/patients/home";
    }

    @RequestMapping(value = "/appointments/{id}/cancel", method = RequestMethod.GET)
    public String cancelAppointment(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        appointmentService.cancelAppointment(id);

        if (request.isUserInRole(AuthorityRole.ROLE_PATIENT.toString())) {
            return "redirect:/patients/home";
        } else {
            return "redirect:/doctors/home";
        }
    }

    @RequestMapping(value = "/appointments/{id}/accept", method = RequestMethod.GET)
    public String acceptAppointment(@PathVariable("id") Integer id, Model model) {

        appointmentService.acceptAppointment(id);
        return "redirect:/doctors/home";
    }

    @RequestMapping(value = "/appointments/{id}/reject", method = RequestMethod.GET)
    public String rejectAppointment(@PathVariable("id") Integer id, Model model) {

        appointmentService.acceptAppointment(id);
        return "redirect:/doctors/home";
    }

    @RequestMapping(value = "/appointments/{id}/detail", method = RequestMethod.GET)
    public String getAppointmentDetail(@ModelAttribute("appointment") Appointment appointment,
                                       @PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        appointment = appointmentService.getAppointment(id);
        model.addAttribute("appointment", appointment);

        if (request.isUserInRole(AuthorityRole.ROLE_PATIENT.toString())) {
            return "appointment-detail";
        } else {
            return "doctor-appointment-detail";
        }
    }

    @RequestMapping(value = "/appointments/update", method = RequestMethod.POST)
    public String updateInvoiceDetail(@ModelAttribute("appointment") Appointment appointment, Model model,
                                      HttpServletRequest request) {

        appointmentService.updateAppointment(appointment);

        return "redirect:/doctors/home";
    }
}

