package ma.usmba.projet_cinema_spring.repositories;

import ma.usmba.projet_cinema_spring.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film, Long> {
}
