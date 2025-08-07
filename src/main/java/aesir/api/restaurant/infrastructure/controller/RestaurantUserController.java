package aesir.api.restaurant.infrastructure.controller;

import aesir.api.restaurant.domain.dto.RestaurantResponseDTO;
import aesir.api.restaurant.domain.dto.RestaurantUserDTO;
import aesir.api.restaurant.domain.dto.RestaurantUserResponseDTO;
import aesir.api.restaurant.domain.exceptions.RestaurantExistsException;
import aesir.api.restaurant.domain.exceptions.UserExistsException;
import aesir.api.restaurant.domain.repository.RestaurantUserRepository;
import aesir.api.restaurant.domain.services.RestaurantUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurantUser")
public class RestaurantUserController {

    @Autowired
    private RestaurantUserService restaurantUserService;

    @PostMapping
    public ResponseEntity<?> createRestaurantUser(@RequestBody @Valid RestaurantUserDTO restaurantUserDTO,
                                                                          UriComponentsBuilder uriComponentsBuilder) throws Exception {
        try {
            RestaurantUserResponseDTO responseDTO = restaurantUserService.createRestaurantUser(restaurantUserDTO);

            URI uri = uriComponentsBuilder.path("/restaurantUser/{id}")
                    .buildAndExpand(responseDTO.id())
                    .toUri();

            return ResponseEntity.created(uri).body(responseDTO);
        } catch (RestaurantExistsException | UserExistsException ex) {
            // Manejo específico si ocurre alguna de las excepciones
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listRestaurants(){
        try{
            List<RestaurantUserResponseDTO> responseDTO = restaurantUserService.listRestaurantUser();

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("We didnt found any Restaurant User");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable Long id) throws Exception {
        try {
            RestaurantUserResponseDTO responseDTO = restaurantUserService.getRestaurantUser(id);

            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("We didnt found any RestaurantUser with id: " + id);
        }

    }
}
