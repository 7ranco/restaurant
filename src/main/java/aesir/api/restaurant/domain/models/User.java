package aesir.api.restaurant.domain.models;

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
    private int phoneNumber;
    private String userName;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;


}
