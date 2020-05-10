package fii.practic.health.boundry.dto;

import fii.practic.health.entity.model.Address;
import fii.practic.health.entity.model.Email;
import fii.practic.health.entity.model.PhoneNumber;

import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

public class PatientDTO {

    private Long id;

    private String firstName;

    private String lastName;

    @PositiveOrZero
    @Min(value = 18)
    private Integer age;

    private EmailDTO email;

    private AddressDTO address;

    private PhoneNumberDTO phoneNumber;
    
    private Long doctorId;

    public PatientDTO(Long id, String firstName, String lastName, Integer age, EmailDTO email, AddressDTO address, PhoneNumberDTO phoneNumber, Long doctorId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.doctorId = doctorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public EmailDTO getEmail() {
        return email;
    }

    public void setEmail(EmailDTO email) {
        this.email = email;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public PhoneNumberDTO getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumberDTO phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
