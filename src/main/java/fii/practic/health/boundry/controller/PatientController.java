package fii.practic.health.boundry.controller;

import fii.practic.health.entity.model.Appointment;
import fii.practic.health.entity.model.Gender;
import fii.practic.health.entity.model.Patient;

import fii.practic.health.control.service.PatientService;
import fii.practic.health.validations.PasswordValidator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/patients")
public class PatientController {

    private PatientService patientService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new PasswordValidator());
    }

    @RequestMapping(value = "/patients/register", method = RequestMethod.GET)
    public String showRegistrationPatient(@ModelAttribute("patient") Patient patient) {
        patient.setGender(Gender.MALE);
        return "patient-registration";
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public String registerPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "patient-registration";
        }
        patientService.save(patient);

        redirectAttributes.addFlashAttribute("patient", patient);
        return "redirect:/patients/successful";
    }

    @RequestMapping(value = "/patients/successful", method = RequestMethod.GET)
    public String showRegistrationSuccessful(@ModelAttribute("patient") Patient patient) {
        return "patient-registration-successful";
    }

    @RequestMapping(value = "/patients/home", method = RequestMethod.GET)
    public String showPatientHome(@ModelAttribute("patient") Patient patient, Model model, Principal principal) {
        Patient foundPatient = patientService.findPatientByEmail(principal.getName());
        Map<Date, List<Appointment>> upcomingAppointment = patientService
                .getUpcomingAppointment(foundPatient.getAppointmentList());
        Map<Date, List<Appointment>> overdueAppointment = patientService
                .getOverdueAppointment(foundPatient.getAppointmentList());

        model.addAttribute("upcomingAppointment", upcomingAppointment);
        model.addAttribute("overdueAppointment", overdueAppointment);
        return "patient-home";
    }
}
