package aesir.api.restaurant.infrastructure.controller;

import aesir.api.restaurant.domain.dto.AddressDTO;
import aesir.api.restaurant.domain.dto.RestaurantDTO;
import aesir.api.restaurant.domain.dto.RestaurantResponseDTO;
import aesir.api.restaurant.domain.models.Restaurant;
import aesir.api.restaurant.domain.repository.RestaurantRepository;
import aesir.api.restaurant.domain.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;
    @PostMapping
    @Transactional
    public ResponseEntity<?> createRestaurant(@RequestBody @Valid RestaurantDTO restaurantDTO,
                                                          UriComponentsBuilder uriComponentsBuilder){
        try{
            RestaurantResponseDTO responseDTO = restaurantService.createRestaurant(restaurantDTO);
            URI uri =uriComponentsBuilder
                    .path("/restaurant/{1}")
                    .buildAndExpand(responseDTO.id())
                    .toUri();

            return ResponseEntity.created(uri).body(responseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Restaurant dont created");
        }
    }

    @GetMapping
    public ResponseEntity<?> getRestaurant(){
        try {
            List<RestaurantResponseDTO> RestaurantResponseDTO = restaurantService.listRestaurants();

            return ResponseEntity.ok(RestaurantResponseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("We didnt found any Restaurant");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantId(@PathVariable Long id){
        try {
            RestaurantResponseDTO RestaurantResponseDTO = restaurantService.getRestaurant(id);

            return ResponseEntity.ok(RestaurantResponseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("We didnt found any Restaurant");
        }

    }
}
