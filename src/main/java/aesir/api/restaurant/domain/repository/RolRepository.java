package aesir.api.restaurant.domain.repository;

import aesir.api.restaurant.domain.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
