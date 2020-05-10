package fii.practic.health.entity.model;

import fii.practic.health.boundry.util.DoctorConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Appointment {

    // It contains appointment date, status, cause

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long appointmentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.NEW;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Doctor.class)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Patient.class)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @DateTimeFormat(pattern = DoctorConstants.DATE_FORMAT)
    private Date date;
//    private Date startTime;

//    private Date endTime;

    @Size(max = 256, message = DoctorConstants.RANGE_LETTERS_VALIDATION_APP)
    private String cause;

    public Appointment(@NotNull AppointmentStatus status, Doctor doctor, Patient patient, Date date, String cause) {
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.cause = cause;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
