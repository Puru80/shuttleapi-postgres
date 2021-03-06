package com.example.shuttleapi.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Ticket
{
    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    private Long id;
    private String origin;
    private String destination;
    private long seats;
    private String timing;
    private String timeOfBooking;
    private boolean paymentStatus;
    private String userEmail;

    public Ticket(String origin, String destination, long seats, String timing, String timeOfBooking,
                  boolean paymentStatus, String userEmail) {
        this.origin = origin;
        this.destination = destination;
        this.seats = seats;
        this.timing = timing;
        this.timeOfBooking = timeOfBooking;
        this.paymentStatus = paymentStatus;
        this.userEmail = userEmail;
    }
}
