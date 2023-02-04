package ma.usmba.projet_cinema_spring.repositories;

import ma.usmba.projet_cinema_spring.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
