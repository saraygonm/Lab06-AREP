package eci.edu.crud.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  // Asegúrate de que esta anotación esté presente
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
