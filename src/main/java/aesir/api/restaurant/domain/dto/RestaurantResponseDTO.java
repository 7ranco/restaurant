package aesir.api.restaurant.domain.dto;

import jakarta.validation.constraints.NotNull;

public record RestaurantResponseDTO(
        @NotNull
        Long id,
        @NotNull
        String nit ,
        @NotNull
        String restaurantName,
        @NotNull
        AddressDTO address,
        @NotNull
        String email,
        @NotNull
        Long phone) {
}
