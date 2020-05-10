package fii.practic.health.entity.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@EnableScheduling
public class Admin extends Person {

    // It contains the site administrator information

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int adminId;

    @Column(name = "staff_number")
    private String staffNumber;

    public Admin(String firstName, String lastName, Address address, @javax.validation.constraints.Email(message = "Must be a valid email") Email email, @NotNull Gender gender, PhoneNumber phoneNumber, @Valid User user, String staffNumber) {
        super(firstName, lastName, address, email, gender, phoneNumber, user);
        this.staffNumber = staffNumber;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }
}