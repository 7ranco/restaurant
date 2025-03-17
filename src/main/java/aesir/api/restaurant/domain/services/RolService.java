package aesir.api.restaurant.domain.services;

import aesir.api.restaurant.domain.dto.RolDTO;
import aesir.api.restaurant.domain.dto.RolResponseDTO;
import aesir.api.restaurant.domain.dto.UserDTO;
import aesir.api.restaurant.domain.dto.UserResponseDTO;
import aesir.api.restaurant.domain.models.Rol;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RolService {

    RolResponseDTO createRol(RolDTO rolDTO) throws Exception;
    List<RolResponseDTO> listRols()throws Exception;
    Rol getRol(Long rolName) throws Exception;

}
