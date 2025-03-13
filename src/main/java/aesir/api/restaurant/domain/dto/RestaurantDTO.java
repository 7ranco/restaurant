package aesir.api.restaurant.domain.dto;

import jakarta.validation.constraints.NotNull;

public record RestaurantDTO(@NotNull
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
