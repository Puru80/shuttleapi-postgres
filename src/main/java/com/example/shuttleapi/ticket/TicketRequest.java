package com.example.shuttleapi.ticket;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TicketRequest
{
    private final String origin;
    private final String destination;
    private final long seats;
    private final String timing;
    private final String email;
}
