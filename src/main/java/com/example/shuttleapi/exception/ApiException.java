package com.example.shuttleapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ApiException
{
    private final String message;
    private final int status;
    private final String reasonPhrase;
    private final ZonedDateTime timeStamp;

}
