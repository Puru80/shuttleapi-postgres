package com.example.shuttleapi.ticket;

import com.example.shuttleapi.ShuttleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket")
@AllArgsConstructor
public class TicketController
{
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<ShuttleResponse> bookTicket(@RequestBody TicketRequest request)
    {
        return new ResponseEntity<>(new ShuttleResponse("Ticket Booked",
                ticketService.saveTicket(request)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/cancellation")
    public ResponseEntity<ShuttleResponse> cancelTicket(@RequestParam("id") Long id)
    {
        return new ResponseEntity<>(new ShuttleResponse("Ticket Cancelled",
                ticketService.cancelTicket(id)), HttpStatus.OK);
    }

    @GetMapping(path = "mytickets")
    @ResponseBody()
    public ResponseEntity<ShuttleResponse> getTickets(@RequestParam("email") String email)
    {
        return new ResponseEntity<>(new ShuttleResponse("Ticket Booked",
                ticketService.getTickets(email)), HttpStatus.OK);
    }

    @PutMapping(path = "updatePaymentStatus")
    public ResponseEntity<ShuttleResponse> updatePaymentStatus(@RequestParam("id") Long id)
    {
        return new ResponseEntity<>(new ShuttleResponse("Ticket Booked",
            ticketService.isPaymentStatus(id)), HttpStatus.OK);
    }
}
