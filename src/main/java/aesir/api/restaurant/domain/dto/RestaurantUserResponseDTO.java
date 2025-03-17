package aesir.api.restaurant.domain.dto;

public record RestaurantUserResponseDTO(
        Long id,
        RestaurantResponseDTO restaurantResponseDTO,
        UserResponseDTO userResponseDTO
) {
}
