package ma.usmba.projet_cinema_spring.web;

import lombok.Data;
import ma.usmba.projet_cinema_spring.entities.Film;
import ma.usmba.projet_cinema_spring.entities.Ticket;
import ma.usmba.projet_cinema_spring.repositories.FilmRepository;
import ma.usmba.projet_cinema_spring.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws Exception {
        Film film = filmRepository.findById(id).get();
        String photoName= film.getPhoto();
        File file= new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm) {
        List<Ticket> listTicket=new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket->{
            Ticket ticket=ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReserve(true);
            ticketRepository.save(ticket);
            listTicket.add(ticket);
        });
        return listTicket;
    }
}

@Data
class TicketForm {
    private String nomClient;
    private int codePayement;
    private List<Long> tickets=new ArrayList<>();
}
