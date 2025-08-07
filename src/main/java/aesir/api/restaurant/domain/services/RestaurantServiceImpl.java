package aesir.api.restaurant.domain.services;

import aesir.api.restaurant.domain.dto.AddressDTO;
import aesir.api.restaurant.domain.dto.RestaurantDTO;
import aesir.api.restaurant.domain.dto.RestaurantResponseDTO;
import aesir.api.restaurant.domain.models.Restaurant;
import aesir.api.restaurant.domain.models.User;
import aesir.api.restaurant.domain.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    @Transactional
    public RestaurantResponseDTO createRestaurant(RestaurantDTO restaurantDTO) throws Exception {
        Restaurant restaurant = restaurantRepository.save(new Restaurant(restaurantDTO));
        return new RestaurantResponseDTO(restaurant.getId(),restaurant.getNit(), restaurant.getRestaurantName(),
                new AddressDTO(restaurantDTO.address().indicacion(), restaurantDTO.address().numero(),
                        restaurantDTO.address().complemento(), restaurantDTO.address().barrio(),
                        restaurantDTO.address().ciudad()), restaurantDTO.email(), restaurantDTO.phone());
    }

    @Override

    public List<RestaurantResponseDTO> listRestaurants() throws Exception {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(res -> {
            return new RestaurantResponseDTO(res.getId(), res.getNit(),
                    res.getRestaurantName(), new AddressDTO(res.getAddress().getIndicacion(),
                    res.getAddress().getNumero(), res.getAddress().getComplemento(),
                    res.getAddress().getBarrio(), res.getAddress().getCiudad()), res.getEmail(),
                    res.getPhone());
        }).toList();
    }

    @Override
    public RestaurantResponseDTO getRestaurant(Long id) throws Exception {
        Restaurant res = restaurantRepository.findById(id).map(Restaurant::new)
                .stream().findFirst()
                .orElseThrow(() -> new Exception("Restaurant not found"));
        return new RestaurantResponseDTO(res.getId(),
                res.getNit(), res.getRestaurantName(),
                new AddressDTO(res.getAddress().getIndicacion(),res.getAddress().getNumero(),
                        res.getAddress().getComplemento(),res.getAddress().getBarrio(),
                        res.getAddress().getCiudad()), res.getEmail(), res.getPhone());
    }

    @Override
    public Optional<Restaurant> getRestaurantById(Long id) throws Exception {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant getRestaurantByNit(String nit) throws Exception {
        return  restaurantRepository.getRestaurantByNit(nit);
    }

}
