package aesir.api.restaurant.domain.dto;

import jakarta.validation.constraints.NotNull;

public record UserResponseDTO(
        @NotNull
        Long id,
        @NotNull
        String cc,
        @NotNull
        String name,
        @NotNull
        String lastName,
        @NotNull
        String email,
        @NotNull
        Long phoneNumber,
        @NotNull
        String userName,
        @NotNull
        String password,
        @NotNull
        RolResponseDTO rolResponseDTO
) {
}
