package fii.practic.health.boundry.dto;

import org.hibernate.validator.constraints.Length;

public class  PhoneNumberDTO {

    private Long id;

    @Length(max = 10)
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
