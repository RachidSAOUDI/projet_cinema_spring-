package ma.usmba.projet_cinema_spring.services;

import jakarta.transaction.Transactional;
import ma.usmba.projet_cinema_spring.entities.*;
import ma.usmba.projet_cinema_spring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PresentationRepository presentationRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void initVilles() {
        Stream.of("Casablanca", "Rabat", "Marrakech", "Tanger").forEach(v->{
           // villeRepository.save(new Ville(null, v));
            Ville ville = new Ville();
            ville.setName(v);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v->{
            Stream.of("Mega Rama", "Mirage", "Dawliz", "Camera", "Royale")
                    .forEach(nameCinema->{
                        Cinema cinema = new Cinema();
                        cinema.setName(nameCinema);
                        cinema.setNombreSalles(3+(int)(Math.random()*7));
                        cinema.setVille(v);
                        cinemaRepository.save(cinema);
                    });
        });
    }


    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0; i < cinema.getNombreSalles(); i++) {
                Salle salle = new Salle();
                salle.setName("Salle "+(i+1));
                salle.setCinema(cinema);
                salle.setNombrePlace(50+(int)(Math.random()*30));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNombrePlace(); i++) {
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s -> {
            Seance seance=new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initCatgories() {
        Stream.of("Histoire", "Action", "Fiction", "Drama").forEach(cat->{
            Categorie categorie=new Categorie();
            categorie.setName(cat);
            categorieRepository.save(categorie);
        });
    }

    @Override
    public void initFilms() {
        double[] durees=new double[] {1,1.5,2,2.5,3};
        List<Categorie> categories=categorieRepository.findAll();
        Stream.of("alexander","titanic","Seigneur des Anneaux","matrix","terminator")
                .forEach(titreFilm->{
                    Film film=new Film();
                    film.setTitre(titreFilm);
                    film.setDuree(durees[new Random().nextInt(durees.length)]);
                    film.setPhoto(titreFilm.replaceAll(" ","")+".jpg");
                    film.setCategorie(categories.get(new Random().nextInt(categories.size())));
                    filmRepository.save(film);
                });
    }

    @Override
    public void initPresentations() {
        double[] prices=new double[] {30,50,60,70,90};
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    filmRepository.findAll().forEach(film -> {
                        seanceRepository.findAll().forEach(seance -> {
                            Presentation presentation =new Presentation();
                            presentation.setDatePresentation(new Date());
                            presentation.setFilm(film);
                            presentation.setPrix(prices[new Random().nextInt(prices.length)]);
                            presentation.setSalle(salle);
                            presentation.setSeance(seance);
                            presentationRepository.save(presentation);
                        });
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        presentationRepository.findAll().forEach(p -> {
            p.getSalle().getPlaces().forEach(place -> {
                Ticket ticket=new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setPresentation(p);
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            });
        });
    }
}
