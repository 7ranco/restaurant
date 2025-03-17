package aesir.api.restaurant.domain.services;

import aesir.api.restaurant.domain.dto.RestaurantResponseDTO;
import aesir.api.restaurant.domain.dto.RestaurantUserDTO;
import aesir.api.restaurant.domain.dto.RestaurantUserResponseDTO;

public interface RestaurantUserService {

    RestaurantUserResponseDTO createRestaurantUser(RestaurantUserDTO restaurantUserDTO) throws Exception;

}
