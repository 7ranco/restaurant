package aesir.api.restaurant.infrastructure.controller;

import aesir.api.restaurant.domain.dto.RestaurantResponseDTO;
import aesir.api.restaurant.domain.dto.UserDTO;
import aesir.api.restaurant.domain.dto.UserResponseDTO;
import aesir.api.restaurant.domain.models.User;
import aesir.api.restaurant.domain.repository.UserRepository;
import aesir.api.restaurant.domain.services.RolService;
import aesir.api.restaurant.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO,
                                        UriComponentsBuilder uriComponentsBuilder) throws Exception {
        try{
            UserResponseDTO userResponseDTO = userService.createUser(userDTO);

            URI uri = uriComponentsBuilder
                    .path("/user/{id}")
                    .buildAndExpand(userResponseDTO.id())
                    .toUri();

            return ResponseEntity.created(uri).body(userResponseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User dont created");
        }


    }

    @GetMapping
    public ResponseEntity<?> listUsers() throws Exception {
        try {
            List<UserResponseDTO> userResponseDTOList = userService.listUsers();

            return ResponseEntity.ok(userResponseDTOList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("We didnt found any user");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws Exception {
        try{

            UserResponseDTO user = userService.getUser(id);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("We didnt found any User with id: "+ id);
        }

    }
}
