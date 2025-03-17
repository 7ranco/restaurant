package aesir.api.restaurant.infrastructure.controller;

import aesir.api.restaurant.domain.dto.RestaurantResponseDTO;
import aesir.api.restaurant.domain.dto.RestaurantUserDTO;
import aesir.api.restaurant.domain.dto.RestaurantUserResponseDTO;
import aesir.api.restaurant.domain.repository.RestaurantUserRepository;
import aesir.api.restaurant.domain.services.RestaurantUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/restaurantUser")
public class RestaurantUserController {

    @Autowired
    private RestaurantUserService restaurantUserService;

    @PostMapping
    public ResponseEntity<?> createRestaurantUser(@RequestBody @Valid RestaurantUserDTO restaurantUserDTO,
                                                                          UriComponentsBuilder uriComponentsBuilder) throws Exception {

            RestaurantUserResponseDTO responseDTO = restaurantUserService.createRestaurantUser(restaurantUserDTO);

            URI uri = uriComponentsBuilder.path("/restaurantUser/{id}")
                    .buildAndExpand(responseDTO.id())
                    .toUri();

            return ResponseEntity.created(uri).body(responseDTO);
    }
}
