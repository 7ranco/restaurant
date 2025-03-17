package aesir.api.restaurant.domain.services;

import aesir.api.restaurant.domain.dto.*;
import aesir.api.restaurant.domain.models.Restaurant;
import aesir.api.restaurant.domain.models.RestaurantUser;
import aesir.api.restaurant.domain.models.Rol;
import aesir.api.restaurant.domain.models.User;
import aesir.api.restaurant.domain.repository.RestaurantRepository;
import aesir.api.restaurant.domain.repository.RestaurantUserRepository;
import aesir.api.restaurant.domain.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RestaurantUserServiceImpl implements RestaurantUserService{

    @Autowired
    private RestaurantUserRepository restaurantUserRepository;

    @Autowired
    private RolService rolService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public RestaurantUserResponseDTO createRestaurantUser(RestaurantUserDTO restaurantUserDTO) throws Exception {

        Rol rol = rolService.getRol(restaurantUserDTO.userDTO().rol());

        UserResponseDTO userResponseDTO = userService.createUser(restaurantUserDTO.userDTO());

        RestaurantResponseDTO restaurantResponseDTO = restaurantService.createRestaurant(restaurantUserDTO.restaurantDTO());

        User user = userService.getUserById(userResponseDTO.id()).get();

        Restaurant restaurant = restaurantService.getRestaurantById(restaurantResponseDTO.id()).get();

        RestaurantUser restaurantUser = restaurantUserRepository.save(new RestaurantUser(restaurant,user));

        RestaurantResponseDTO responseRestaurantDTO = new RestaurantResponseDTO(restaurantUser.getRestaurant().getId(),
                restaurantUser.getRestaurant().getNit(), restaurantUser.getRestaurant().getRestaurantName(),
                new AddressDTO(restaurantUser.getRestaurant().getAddress().getIndicacion(),restaurantUser.getRestaurant().getAddress().getNumero(),
                        restaurantUser.getRestaurant().getAddress().getComplemento(),restaurantUser.getRestaurant().getAddress().getBarrio(),
                        restaurantUser.getRestaurant().getAddress().getCiudad()), restaurantUser.getRestaurant().getEmail(), restaurantUser.getRestaurant().getPhone());

        UserResponseDTO responseUserDTO = new UserResponseDTO(restaurantUser.getUser().getId(),
                restaurantUser.getUser().getCc(), restaurantUser.getUser().getName(), restaurantUser.getUser().getLastName(),
                restaurantUser.getUser().getEmail(), restaurantUser.getUser().getPhoneNumber(), restaurantUser.getUser().getUserName(),
                restaurantUser.getUser().getPassword(),new RolResponseDTO(restaurantUser.getUser().getRol().getId(), restaurantUser.getUser().getRol().getRolName()));

        return new RestaurantUserResponseDTO(restaurantUser.getId(), responseRestaurantDTO, responseUserDTO);
    }
}
