package ma.usmba.projet_cinema_spring;

import ma.usmba.projet_cinema_spring.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetCinemaSpringApplication implements CommandLineRunner {
    @Autowired
    private CinemaService cinemaService;

    public static void main(String[] args) {
        SpringApplication.run(ProjetCinemaSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cinemaService.initVilles();
        cinemaService.initCinemas();
        cinemaService.initSalles();
        cinemaService.initPlaces();
        cinemaService.initSeances();
        cinemaService.initCatgories();
        cinemaService.initFilms();
        cinemaService.initProjections();
        cinemaService.initTickets();
    }
}
