package aesir.api.restaurant.infrastructure.controller;

import aesir.api.restaurant.domain.dto.AddressDTO;
import aesir.api.restaurant.domain.dto.RestaurantDTO;
import aesir.api.restaurant.domain.models.Restaurant;
import aesir.api.restaurant.domain.repository.RestaurantRepository;
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

    @PostMapping
    @Transactional
    public ResponseEntity<?> createRestaurant(@RequestBody @Valid RestaurantDTO restaurantDTO,
                                                          UriComponentsBuilder uriComponentsBuilder){

        try{
            Restaurant restaurant = restaurantRepository.save(new Restaurant(restaurantDTO));
            RestaurantDTO responseDTO =new RestaurantDTO(restaurant.getNit(), restaurant.getRestaurantName(),
                    new AddressDTO(restaurantDTO.address().indicacion(), restaurantDTO.address().numero(),
                            restaurantDTO.address().complemento(), restaurantDTO.address().barrio(),
                            restaurantDTO.address().ciudad()), restaurantDTO.email(), restaurantDTO.phone());
            URI uri =uriComponentsBuilder
                    .path("/restaurant/{1}")
                    .buildAndExpand(restaurant.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(responseDTO);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Restaurant dont created");
        }
    }

    @GetMapping
    public ResponseEntity<?> getRestaurant(){
        List<Restaurant> restaurants = restaurantRepository.findAll();


        System.out.println("Restaurantes encontrados: " + restaurants.size());
        try {


            List<RestaurantDTO> restaurantDTOS = restaurants.stream().map(res -> {
                return new RestaurantDTO(res.getNit(),
                        res.getRestaurantName(), new AddressDTO(res.getAddress().getIndicacion(),
                        res.getAddress().getNumero(), res.getAddress().getComplemento(),
                        res.getAddress().getBarrio(), res.getAddress().getCiudad()), res.getEmail(),
                        res.getPhone());
            }).toList();



            return ResponseEntity.ok(restaurantDTOS);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("We dont found any Restaurant");
        }

    }
}
