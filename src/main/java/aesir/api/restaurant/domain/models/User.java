package aesir.api.restaurant.domain.models;

import aesir.api.restaurant.domain.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Validated
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    private String cc;
    private String name;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String userName;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolId")
    private Rol rol;

    public User(UserDTO userDTO, Rol rol){
        this.cc = userDTO.cc();
        this.name = userDTO.name();
        this.lastName = userDTO.lastName();
        this.email = userDTO.email();
        this.phoneNumber = userDTO.phoneNumber();
        this.userName = userDTO.userName();
        this.password = userDTO.password();
        this.rol =rol;
    }
    public User(User user){
        this.cc = user.getCc();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.rol =user.rol;
    }

    public User(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
