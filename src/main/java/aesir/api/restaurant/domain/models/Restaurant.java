package aesir.api.restaurant.domain.models;

import aesir.api.restaurant.domain.dto.RestaurantDTO;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Data
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nit;
    private String restaurantName;

    @Embedded
    private Address address;

    private String email;
    private Long phone;

    public Restaurant(Restaurant restaurant) {
        this.id = restaurant.id;
        this.nit = restaurant.getNit();
        this.restaurantName = restaurant.getRestaurantName();
        this.address = restaurant.getAddress();
        this.email = restaurant.getEmail();
        this.phone = restaurant.getPhone();
    }

    public Restaurant(RestaurantDTO restaurantDTO) {
        this.nit = restaurantDTO.nit();
        this.restaurantName = restaurantDTO.restaurantName();
        this.address = new Address(restaurantDTO.address());
        this.email = restaurantDTO.email();
        this.phone = restaurantDTO.phone();
    }

    public Restaurant(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }


}
