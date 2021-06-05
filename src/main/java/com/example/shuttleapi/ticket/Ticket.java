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
    private String destination;
    private long seats;
    private LocalDateTime timeOfBooking;
    private boolean paymentStatus;
    private String userEmail;

    public Ticket(String destination, long seats, LocalDateTime timeOfBooking,
                  boolean paymentStatus, String userEmail)
    {
        this.destination = destination;
        this.seats = seats;
        this.timeOfBooking = timeOfBooking;
        this.paymentStatus = paymentStatus;
        this.userEmail = userEmail;
    }
}
