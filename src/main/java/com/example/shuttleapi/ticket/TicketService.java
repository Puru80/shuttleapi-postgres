package com.example.shuttleapi.ticket;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService
{
    private final TicketRepository ticketRepository;

    public String saveTicket(TicketRequest ticket)
    {
        ticketRepository.save(new Ticket(
                ticket.getDestination(),
                ticket.getSeats(),
                LocalDateTime.now(),
                false,
                ticket.getEmail()
        ));

        return "Confirmed";
    }

    public String cancelTicket(Long id)
    {
        Ticket ticket = ticketRepository.findTicketById(id);
        if(ticket.isPaymentStatus())
            throw new IllegalStateException("Ticket can't be cancelled as Payment has been done");

        ticketRepository.delete(ticket);

        return "Success";
    }

    public List<Ticket> getTickets(String email){
        return ticketRepository.getTicketsByEmail(email);
    }

    public String isPaymentStatus(Long id) {
        ticketRepository.updatePaymentStatus(id);

        return "Payment Status Updated";
    }
}
