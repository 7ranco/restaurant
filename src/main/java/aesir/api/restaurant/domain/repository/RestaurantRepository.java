package aesir.api.restaurant.domain.repository;

import aesir.api.restaurant.domain.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
