package aesir.api.restaurant.domain.services;

import aesir.api.restaurant.domain.dto.*;
import aesir.api.restaurant.domain.exceptions.RestaurantExistsException;
import aesir.api.restaurant.domain.exceptions.UserExistsException;
import aesir.api.restaurant.domain.models.Restaurant;
import aesir.api.restaurant.domain.models.RestaurantUser;
import aesir.api.restaurant.domain.models.Rol;
import aesir.api.restaurant.domain.models.User;
import aesir.api.restaurant.domain.repository.RestaurantRepository;
import aesir.api.restaurant.domain.repository.RestaurantUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Lazy
    private UserService userService;

    @Override
    @Transactional
    public RestaurantUserResponseDTO createRestaurantUser(RestaurantUserDTO restaurantUserDTO) throws Exception {

        Rol rol = rolService.getRolId(restaurantUserDTO.userDTO().rol());

        Restaurant rest = restaurantService.getRestaurantByNit(restaurantUserDTO.restaurantDTO().nit());

        User use = userService.getUserByCc(restaurantUserDTO.userDTO().cc());

        if (rest != null){
            throw new RestaurantExistsException(restaurantUserDTO.restaurantDTO().nit());
        }

        if( use != null){
            throw new UserExistsException(restaurantUserDTO.userDTO().cc());
        }

        UserResponseDTO userResponseDTO = userService.createUser(restaurantUserDTO.userDTO());

        RestaurantResponseDTO restaurantResponseDTO = restaurantService.createRestaurant(restaurantUserDTO.restaurantDTO());

        User user = userService.getUserById(userResponseDTO.id()).get();

        Restaurant restaurant = restaurantService.getRestaurantById(restaurantResponseDTO.id()).get();

        RestaurantUser restaurantUser = restaurantUserRepository.save(new RestaurantUser(restaurant,user));

        RestaurantResponseDTO responseRestaurantDTO = new RestaurantResponseDTO(restaurantUser.getRestaurant().getId(),
                restaurantUser.getRestaurant().getNit(), restaurantUser.getRestaurant().getRestaurantName(),
                new AddressDTO(restaurantUser.getRestaurant().getAddress().getIndicacion(),
                        restaurantUser.getRestaurant().getAddress().getNumero(),
                        restaurantUser.getRestaurant().getAddress().getComplemento(),restaurantUser.getRestaurant().getAddress().getBarrio(),
                        restaurantUser.getRestaurant().getAddress().getCiudad()), restaurantUser.getRestaurant().getEmail(), restaurantUser.getRestaurant().getPhone());

        UserResponseDTO responseUserDTO = new UserResponseDTO(restaurantUser.getUser().getId(),
                restaurantUser.getUser().getCc(), restaurantUser.getUser().getName(), restaurantUser.getUser().getLastName(),
                restaurantUser.getUser().getEmail(), restaurantUser.getUser().getPhoneNumber(), restaurantUser.getUser().getUserName(),
                restaurantUser.getUser().getPassword(),new RolResponseDTO(restaurantUser.getUser().getRol().getId(), restaurantUser.getUser().getRol().getRolName()));

        return new RestaurantUserResponseDTO(restaurantUser.getId(), responseRestaurantDTO, responseUserDTO);
    }

    @Override
    public List<RestaurantUserResponseDTO> listRestaurantUser() throws Exception {
        List<RestaurantUser> restaurantUser = restaurantUserRepository.findAll();

        return restaurantUser.stream().map(resUs ->{
            return new RestaurantUserResponseDTO(resUs.getId(), new RestaurantResponseDTO(resUs.getRestaurant().getId(),
                resUs.getRestaurant().getNit(), resUs.getRestaurant().getRestaurantName(),
                new AddressDTO(resUs.getRestaurant().getAddress().getIndicacion(),resUs.getRestaurant().getAddress().getNumero(),
                        resUs.getRestaurant().getAddress().getComplemento(),resUs.getRestaurant().getAddress().getBarrio(),
                        resUs.getRestaurant().getAddress().getCiudad()), resUs.getRestaurant().getEmail(), resUs.getRestaurant().getPhone()),
                new UserResponseDTO(resUs.getUser().getId(), resUs.getUser().getCc(), resUs.getUser().getName(), resUs.getUser().getLastName(),
                        resUs.getUser().getEmail(), resUs.getUser().getPhoneNumber(), resUs.getUser().getUserName(),
                        resUs.getUser().getPassword(),
                        new RolResponseDTO(resUs.getUser().getRol().getId(), resUs.getUser().getRol().getRolName())));
        }).toList();
    }

    @Override
    public RestaurantUserResponseDTO getRestaurantUser(Long id) throws Exception {
            RestaurantUser res = restaurantUserRepository.findById(id).get();

            return new RestaurantUserResponseDTO(res.getId(), new RestaurantResponseDTO(res.getRestaurant().getId(),
                    res.getRestaurant().getNit(), res.getRestaurant().getRestaurantName(),
                    new AddressDTO(res.getRestaurant().getAddress().getIndicacion(), res.getRestaurant().getAddress().getNumero(),
                            res.getRestaurant().getAddress().getComplemento(), res.getRestaurant().getAddress().getBarrio(),
                            res.getRestaurant().getAddress().getCiudad()), res.getRestaurant().getEmail(), res.getRestaurant().getPhone()),
                    new UserResponseDTO(res.getUser().getId(), res.getUser().getCc(), res.getUser().getName(), res.getUser().getLastName(),
                            res.getUser().getEmail(), res.getUser().getPhoneNumber(), res.getUser().getUserName(),
                            res.getUser().getPassword(),
                            new RolResponseDTO(res.getUser().getRol().getId(), res.getUser().getRol().getRolName())));

    }
}
