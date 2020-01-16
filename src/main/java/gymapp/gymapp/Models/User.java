package gymapp.gymapp.Models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


import java.util.Date;

@Entity
@Table(name = "user")
public class User {

    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "a_package")
    private Package a_package;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "registration")
    private Date registration;
    @Column(name = "active")
    private boolean active;

    @PrePersist
    protected void prePersist() {
        if (this.registration == null) registration = new Date();
    }



    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "a_package", nullable = false)
    public Package getA_package() {
        return a_package;
    }

    public void setA_package(Package a_package) {
        this.a_package = a_package;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
