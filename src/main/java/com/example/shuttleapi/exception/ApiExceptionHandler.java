package com.example.shuttleapi.exception;

import com.example.shuttleapi.ShuttleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ShuttleResponse> handleExceptionArgument(IllegalArgumentException ex, WebRequest webRequest)
    {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                ex.getMessage(),
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
        );

        return new ResponseEntity<ShuttleResponse>(new ShuttleResponse("Bad Request", apiException),
                httpStatus);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ShuttleResponse> handleExceptionState(IllegalStateException ex, WebRequest webRequest)
    {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                ex.getMessage(),
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
        );

        return new ResponseEntity<ShuttleResponse>(new ShuttleResponse("Bad Request", apiException),
                httpStatus);
    }
}
