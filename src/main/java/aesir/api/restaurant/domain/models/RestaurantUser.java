package aesir.api.restaurant.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Data
@Table(name = "restaurantUser")
public class RestaurantUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public RestaurantUser(Restaurant restaurant, User user) {
        this.restaurant = restaurant;
        this.user = user;
    }

    public RestaurantUser(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
