package aesir.api.restaurant.domain.dto;


import jakarta.validation.constraints.NotNull;

public record RestaurantUserDTO(
        @NotNull
        RestaurantDTO restaurantDTO,
        @NotNull
        UserDTO userDTO
) {
}
