package aesir.api.restaurant.domain.services;

import aesir.api.restaurant.domain.dto.RestaurantDTO;
import aesir.api.restaurant.domain.dto.RestaurantResponseDTO;
import aesir.api.restaurant.domain.dto.RolDTO;
import aesir.api.restaurant.domain.dto.RolResponseDTO;
import aesir.api.restaurant.domain.models.Restaurant;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    RestaurantResponseDTO createRestaurant(RestaurantDTO restaurantDTO) throws Exception;
    List<RestaurantResponseDTO> listRestaurant()throws Exception;
    RestaurantResponseDTO getRestaurant(Long id) throws Exception;
}
