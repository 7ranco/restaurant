package aesir.api.restaurant.domain.services;

import aesir.api.restaurant.domain.dto.RestaurantResponseDTO;
import aesir.api.restaurant.domain.dto.RestaurantUserDTO;
import aesir.api.restaurant.domain.dto.RestaurantUserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface RestaurantUserService {

    RestaurantUserResponseDTO createRestaurantUser(RestaurantUserDTO restaurantUserDTO) throws Exception;

    List<RestaurantUserResponseDTO> listRestaurantUser() throws Exception;

    RestaurantUserResponseDTO getRestaurantUser(Long id) throws Exception;
}
