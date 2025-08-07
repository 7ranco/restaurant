package aesir.api.restaurant.domain.repository;

import aesir.api.restaurant.domain.models.Category;
import aesir.api.restaurant.domain.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("""
            select r from Restaurant r
                        where r.nit = :nit
            """)
    Restaurant getRestaurantByNit(String nit);
}
