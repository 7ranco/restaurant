package aesir.api.restaurant.infrastructure.controller;

import aesir.api.restaurant.domain.dto.AddressDTO;
import aesir.api.restaurant.domain.dto.RestaurantDTO;
import aesir.api.restaurant.domain.dto.RolDTO;
import aesir.api.restaurant.domain.models.Restaurant;
import aesir.api.restaurant.domain.models.Rol;
import aesir.api.restaurant.domain.repository.RolRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolRepository rolRepository;
    @PostMapping
    public ResponseEntity<?> createRol(@RequestBody @Valid RolDTO rolDTO, UriComponentsBuilder  uriComponentsBuilder){
        Rol rol = new Rol(rolDTO);
        rolRepository.save(rol);
        URI uri = uriComponentsBuilder.path("/rol/{id}")
                .buildAndExpand(rol.getId())
                .toUri();

        RolDTO responseDTO = new RolDTO(rol.getRolName());

        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<?> getRol(){
        List<Rol> rol = rolRepository.findAll();


        try {
            List<RolDTO> rolDTOS = rol.stream().map(res -> {
                return new RolDTO(res.getRolName());
            }).toList();

            return ResponseEntity.ok(rolDTOS);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("We dont found any Rol");
        }

    }
}

