package aesir.api.restaurant.domain.repository;

import aesir.api.restaurant.domain.models.RestaurantUser;
import aesir.api.restaurant.infrastructure.controller.UserController;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, Long> {
    }
