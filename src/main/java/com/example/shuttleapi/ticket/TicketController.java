package com.example.shuttleapi.ticket;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket")
@AllArgsConstructor
public class TicketController
{
    private final TicketService ticketService;

    @PostMapping
    public String bookTicket(@RequestBody TicketRequest request)
    {
        return ticketService.saveTicket(request);
    }

    @DeleteMapping(path = "/cancellation")
    public String cancelTicket(@RequestParam("id") Long id)
    {
        return ticketService.cancelTicket(id);
    }

    @GetMapping(path = "mytickets")
    @ResponseBody()
    public List<Ticket> getTickets(@RequestParam("email") String email)
    {
        return ticketService.getTickets(email);
    }

    @PutMapping(path = "updatePaymentStatus")
    public String updatePaymentStatus(@RequestParam("id") Long id)
    {
        ticketService.isPaymentStatus(id);
        return "Payment Confirmed";
    }
}
