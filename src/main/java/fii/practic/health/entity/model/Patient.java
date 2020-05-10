package fii.practic.health.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
public class Patient extends Person{

    // It keeps the patient information

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Min(value = 18)
    private Integer age;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Doctor doctor;

    @Valid
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private List<Appointment> appointmentList;

    public Patient(String firstName, String lastName, Address address, @javax.validation.constraints.Email(message = "Must be a valid email") Email email, @NotNull Gender gender, PhoneNumber phoneNumber, @Valid User user, @PositiveOrZero @Min(value = 18) Integer age, Doctor doctor, @Valid List<Appointment> appointmentList) {
        super(firstName, lastName, address, email, gender, phoneNumber, user);
        this.age = age;
        this.doctor = doctor;
        this.appointmentList = appointmentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
