package ma.usmba.projet_cinema_spring.repositories;

import ma.usmba.projet_cinema_spring.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle, Long> {
}
