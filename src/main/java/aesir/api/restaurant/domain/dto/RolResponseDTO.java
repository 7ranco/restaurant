package aesir.api.restaurant.domain.dto;

import jakarta.validation.constraints.NotNull;

public record RolResponseDTO(
        @NotNull
        Long id,
        @NotNull
        String rolName
) {
}
