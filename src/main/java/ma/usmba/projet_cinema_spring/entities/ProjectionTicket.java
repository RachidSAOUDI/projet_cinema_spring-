package ma.usmba.projet_cinema_spring.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ticketProj", types = Ticket.class)
public interface ProjectionTicket {
    Long getId();
    String getNomClient();
    double getPrix();
    Integer getCodePayement();
    boolean getReserve();
    Place getPlace();
}
