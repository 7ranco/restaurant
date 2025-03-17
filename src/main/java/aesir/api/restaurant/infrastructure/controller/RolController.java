package aesir.api.restaurant.infrastructure.controller;

import aesir.api.restaurant.domain.dto.*;
import aesir.api.restaurant.domain.models.Restaurant;
import aesir.api.restaurant.domain.models.Rol;
import aesir.api.restaurant.domain.repository.RolRepository;
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
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;
    @PostMapping
    public ResponseEntity<?> createRol(@RequestBody @Valid RolDTO rolDTO, UriComponentsBuilder  uriComponentsBuilder){
       try{

           RolResponseDTO responseDTO = rolService.createRol(rolDTO);

           URI uri = uriComponentsBuilder.path("/rol/{id}")
                   .buildAndExpand(responseDTO.id())
                   .toUri();

           return ResponseEntity.created(uri).body(responseDTO);

       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rol dont created");
       }

    }

    @GetMapping
    public ResponseEntity<?> listRol(){
       try{
           List<RolResponseDTO> rolResponseDTO = rolService.listRols();
            return ResponseEntity.ok(rolResponseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("We didnt found any Rol");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRol(@PathVariable Long id) throws Exception {
        try{

            RolResponseDTO rol = rolService.getRol(id);
            return ResponseEntity.ok(rol);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("We didnt found any User with id: "+ id);
        }

    }
}

