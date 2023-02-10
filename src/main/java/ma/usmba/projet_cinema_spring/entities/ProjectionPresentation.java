package ma.usmba.projet_cinema_spring.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name = "p1",types ={Presentation.class})
public interface ProjectionPresentation {
    Long getId();
    double getPrix();
    Date getDatePresentation();
    Salle getSalle();
    Film getFilm();
    Seance getSeance();
    Collection<Ticket> getTickets();
}
