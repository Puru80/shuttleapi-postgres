package com.example.shuttleapi.registration;

import com.example.shuttleapi.appuser.AppUser;
import com.example.shuttleapi.exception.ApiRequestException;
import com.example.shuttleapi.ticket.TicketRequest;
import com.example.shuttleapi.ticket.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController
{
    private final RegistrationService registrationService;
    private final TicketService ticketService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request)
    {
//        throw new ApiRequestException("Testing Exception");
        return registrationService.register(request);
    }

    @GetMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public String logIn(@RequestParam("email") String email, @RequestParam("password") String password)
    {
        return registrationService.login(email, password);
    }

    @GetMapping(path = "/userdetails")
    public AppUser getUserDetails(@RequestParam("email") String email)
    {
        return registrationService.getUser(email);
    }

    @GetMapping(path = "/logout")
    public String logOut(@RequestParam("email") String email)
    {
        return registrationService.logout(email);
    }

    @GetMapping(path = "/confirm")
    public String confirmation(@RequestParam("token") String token)
    {
        return registrationService.confirmToken(token);
    }


}
