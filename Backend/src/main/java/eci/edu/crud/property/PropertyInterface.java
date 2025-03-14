package eci.edu.crud.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyInterface extends JpaRepository<Properties, Long> {
    // Métodos personalizados si es necesario
}
