package ma.usmba.projet_cinema_spring.repositories;

import ma.usmba.projet_cinema_spring.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, Long> {
}
