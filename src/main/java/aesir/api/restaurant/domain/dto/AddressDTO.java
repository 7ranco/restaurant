package aesir.api.restaurant.domain.dto;

import jakarta.validation.constraints.NotNull;

public record AddressDTO(@NotNull
                         String indicacion,
                         @NotNull
                         int numero,
                         @NotNull
                         String complemento,
                         @NotNull
                         String barrio,
                         @NotNull
                         String ciudad) {
}
