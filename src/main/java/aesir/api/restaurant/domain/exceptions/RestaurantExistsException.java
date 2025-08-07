package aesir.api.restaurant.domain.exceptions;

public class RestaurantExistsException extends RuntimeException{

    public RestaurantExistsException(String nit) {
        super("A restaurant with NIT " + nit + " already exists.");
    }

}
