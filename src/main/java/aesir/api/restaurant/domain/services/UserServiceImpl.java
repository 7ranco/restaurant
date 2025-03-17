package aesir.api.restaurant.domain.services;


import aesir.api.restaurant.domain.dto.RolResponseDTO;
import aesir.api.restaurant.domain.dto.UserDTO;
import aesir.api.restaurant.domain.dto.UserResponseDTO;
import aesir.api.restaurant.domain.models.Rol;
import aesir.api.restaurant.domain.models.User;
import aesir.api.restaurant.domain.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private RolService rolService;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserResponseDTO createUser(UserDTO userDTO) throws Exception {
            Rol rol = rolService.getRol(userDTO.rol());

            User user = userRepository.save(new User(userDTO, rol));

            UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(), user.getCc(),
                    user.getName(), userDTO.lastName(), userDTO.email(), user.getPhoneNumber(),
                    user.getUserName(), user.getPassword(), new RolResponseDTO(user.getRol().getId(),
                    user.getRol().getRolName()));

            return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> getUsers() throws Exception {
        List<User> userList = userRepository.findAll();

        List<UserResponseDTO> userResponseDTOS = userList.stream().map(u ->{
            return new UserResponseDTO(u.getId(), u.getCc(),u.getName(), u.getLastName(),
                    u.getEmail(), u.getPhoneNumber(),u.getUserName(),u.getPassword(),
                    new RolResponseDTO(u.getRol().getId(), u.getRol().getRolName()));
        }).toList();
        return userResponseDTOS;
    }

    @Override
    public User getUser(Long id) throws Exception {
        return userRepository.findById(id).map(User::new)
                .stream().findFirst()
                .orElseThrow(() -> new Exception("User not found"));

    }
}
