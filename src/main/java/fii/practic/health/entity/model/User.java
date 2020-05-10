package fii.practic.health.entity.model;

import fii.practic.health.boundry.util.DoctorConstants;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User extends Email{

    //It contains the login information

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @NotBlank(message = DoctorConstants.EMPTY_VALIDATION)
    @javax.validation.constraints.Email(message = DoctorConstants.EMAIL_VALIDATION)
    @Column(name = "email")
    private Email email;

    @Size(min = 5, max = 100, message = DoctorConstants.RANGE_LETTERS_VALIDATION)
    @Column(name = "password")
    private String password;

    @Size(min = 5, max = 100, message = DoctorConstants.RANGE_LETTERS_VALIDATION)
    @Transient
    private String confirmPassword;

    @NotNull
    @Column(name = "enabled")
    private Boolean enabled;

    @Valid
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private List<Authority> authorities;


     //Constructor for User


    public User(@NotBlank(message = DoctorConstants.EMPTY_VALIDATION) @javax.validation.constraints.Email(message = DoctorConstants.EMAIL_VALIDATION) Email email, @Size(min = 5, max = 100, message = DoctorConstants.RANGE_LETTERS_VALIDATION) String password, @Size(min = 5, max = 100, message = DoctorConstants.RANGE_LETTERS_VALIDATION) String confirmPassword, @NotNull Boolean enabled, @Valid List<Authority> authorities) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}