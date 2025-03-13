package aesir.api.restaurant.domain.dto;

import aesir.api.restaurant.domain.models.Restaurant;
import jakarta.validation.constraints.NotNull;

public record RolDTO(
        @NotNull
        String rolName
) {
}
