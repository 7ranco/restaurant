package aesir.api.restaurant.domain.repository;

import aesir.api.restaurant.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
            select u from User u
                        where u.cc = :cc
            """)
    User getUserByCc(String cc);
}
