package ma.usmba.projet_cinema_spring;

import ma.usmba.projet_cinema_spring.entities.Film;
import ma.usmba.projet_cinema_spring.entities.Salle;
import ma.usmba.projet_cinema_spring.entities.Ticket;
import ma.usmba.projet_cinema_spring.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ProjetCinemaSpringApplication implements CommandLineRunner {
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private RepositoryRestConfiguration restConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(ProjetCinemaSpringApplication.class, args);
    }

    @Override
    public void run(String... args) {
        restConfiguration.exposeIdsFor(Film.class,Salle.class, Ticket.class);
        cinemaService.initVilles();
        cinemaService.initCinemas();
        cinemaService.initSalles();
        cinemaService.initPlaces();
        cinemaService.initSeances();
        cinemaService.initCatgories();
        cinemaService.initFilms();
        cinemaService.initPresentations();
        cinemaService.initTickets();
    }
}
