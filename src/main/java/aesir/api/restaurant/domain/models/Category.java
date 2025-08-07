package aesir.api.restaurant.domain.models;

import aesir.api.restaurant.domain.dto.CategoryDTO;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    public Category(CategoryDTO categoryDTO, Restaurant restaurant) {
        this.categoryName = categoryDTO.categoryName();
        this.restaurant = restaurant;
    }

    public Category(){}
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
