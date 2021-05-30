package com.example.shuttleapi.exception;

import com.example.shuttleapi.ShuttleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler
{
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ShuttleResponse> handleApiRequestException(ApiRequestException e)
    {
        //1: Creating the payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest.value(),
                badRequest.getReasonPhrase(),
                ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
        );

        //2: Return response entity
        return new ResponseEntity<ShuttleResponse>(new ShuttleResponse("Bad Request", apiException),
                badRequest);
    }
}
