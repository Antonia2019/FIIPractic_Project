package fii.practic.health.entity.model;
import fii.practic.health.boundry.util.DoctorConstants;
import fii.practic.health.validations.Phone;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@MappedSuperclass
public abstract class Person {

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @Size(min = 4, max = 50, message = "Last name is invalid")
    private String lastName;

    @Valid
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_ID")
    private Address address;

    @javax.validation.constraints.Email(message = "Must be a valid email") //
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_id")
    private Email email;

    @Phone
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_number_id")
    @Size(max = 10, message = DoctorConstants.EXACTLY_DIGITS_VALIDATION)
    private PhoneNumber phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.FEMALE;

    @Valid
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Person(String firstName, String lastName, Address address,
                  @javax.validation.constraints.Email(message = "Must be a valid email")
                          Email email, @NotNull Gender gender, PhoneNumber phoneNumber, @Valid User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.user = user;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
