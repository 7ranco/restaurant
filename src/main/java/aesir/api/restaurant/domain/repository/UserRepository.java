package aesir.api.restaurant.domain.repository;

import aesir.api.restaurant.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
