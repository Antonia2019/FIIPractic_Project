package fii.practic.health.entity.model;

import javax.persistence.*;

@Entity(name = "Authority")
public class Authority extends Email{

    /// This class along with User helps user login and role based security control


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    @Column(name = "EMAIL")
//    private Email email;

    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityRole authorityRole;

    public Authority() {
        // default constructor
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Email getEmail() {
//        return email;
//    }
//
//    public void setEmail(Email email) {
//        this.email = email;
//    }

    public AuthorityRole getAuthorityRole() {
        return authorityRole;
    }

    public void setAuthorityRole(AuthorityRole authorityRole) {
        this.authorityRole = authorityRole;
    }
}