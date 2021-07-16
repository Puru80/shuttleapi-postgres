package com.example.shuttleapi.ticket;

import com.example.shuttleapi.appuser.AppUser;
import com.example.shuttleapi.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@Service
@AllArgsConstructor
public class TicketService
{
    private final TicketRepository ticketRepository;
    private final AppUserService appUserService;

    public String saveTicket(TicketRequest ticket)
    {
        AppUser user = appUserService.findByUserEmail(ticket.getEmail());

        if(user==null)
            throw new IllegalArgumentException("User Not Found");

        LocalDateTime dateTime = LocalDateTime.now(TimeZone.getDefault().toZoneId());

        ticketRepository.save(new Ticket(
                ticket.getDestination(),
                ticket.getSeats(),
                dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
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
