package fii.practic.health.entity.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Doctor extends Person {

    // It keeps the doctor information,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String speciality;

    @Valid
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private List<Appointment> appointmentList;

    public Doctor(String firstName, String lastName, Address address, @javax.validation.constraints.Email(message = "Must be a valid email") Email email, @NotNull Gender gender, PhoneNumber phoneNumber, @Valid User user, String speciality, @Valid List<Appointment> appointmentList) {
        super(firstName, lastName, address, email, gender, phoneNumber, user);
        this.speciality = speciality;
        this.appointmentList = appointmentList;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
