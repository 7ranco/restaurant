package aesir.api.restaurant.domain.services;

import aesir.api.restaurant.domain.dto.UserDTO;
import aesir.api.restaurant.domain.dto.UserResponseDTO;
import aesir.api.restaurant.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService{
    UserResponseDTO createUser(UserDTO userDTO) throws Exception;

    List<UserResponseDTO> listUsers() throws Exception;

    UserResponseDTO getUser(Long id) throws Exception;
    Optional<User> getUserById(Long id) throws Exception;
}
