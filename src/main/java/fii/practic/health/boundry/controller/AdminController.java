package fii.practic.health.boundry.controller;

import fii.practic.health.control.service.AppointmentService;
import fii.practic.health.control.service.DoctorService;
import fii.practic.health.entity.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("/admin")
    public String adminDashboard(Model model) {
        List<Doctor> doctors = doctorService.getAll();
        model.addAttribute("doctors", doctors);
        return "admin-dashboard";
    }

    public String addDoctorAccount(@ModelAttribute("newDoctor") Doctor newDoctor, Model model) {
        model.addAttribute("specializations", appointmentService.getAllSpecialization());
        return "admin-add-doctor";
    }

    @RequestMapping(value = "/admin/save-doctor", method = RequestMethod.POST)
    public String saveDoctor(@Valid @ModelAttribute("newDoctor") Doctor newDoctor, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (!newDoctor.getUser().getPassword().isEmpty()
                && !newDoctor.getUser().getPassword().equals(newDoctor.getUser().getConfirmPassword())) {
            ObjectError err = new ObjectError("passwordMath", "Password and Confirm Password do not match");

            bindingResult.addError(err);
        }

        if (bindingResult.hasErrors()) {
            return "admin-add-doctor";
        }

        doctorService.save(newDoctor);

        redirectAttributes.addFlashAttribute("message", "Doctor successfully added!");
        return "redirect:/admin/add-doctor";
    }

    @RequestMapping(value = "/admin/doctor/{doctorId}", method = RequestMethod.GET)
    public String modifyAdminAccount(@PathVariable int doctorId, Model model) {

        Doctor doctor = doctorService.findDoctorById(doctorId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("specializations", appointmentService.getAllSpecialization());

        return "admin-doctor-account";
    }

    @RequestMapping(value = "/admin/doctor/{doctorId}", method = RequestMethod.POST)
    public String editDoctor(@Valid Doctor doctor, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!doctor.getUser().getPassword().isEmpty()
                && !doctor.getUser().getPassword().equals(doctor.getUser().getConfirmPassword())) {
            ObjectError err = new ObjectError("passwordMath", "Password and Confirm Password do not match");

            bindingResult.addError(err);
        }

        if (bindingResult.hasErrors() && ((!bindingResult.hasFieldErrors("user.password")
                && !bindingResult.hasFieldErrors("user.confirmPassword"))
                || ((bindingResult.hasFieldErrors("user.password")
                && bindingResult.hasFieldErrors("user.confirmPassword")
                && bindingResult.getErrorCount() > 2)))) {

            return "admin-doctor-account";
        }

        doctorService.update(doctor);
        redirectAttributes.addFlashAttribute("message", "Doctor info updated!");

        return "redirect:/admin/doctor/" + doctor.getDoctorId();
    }
}
