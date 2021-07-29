package com.example.shuttleapi.registration;

import com.example.shuttleapi.ShuttleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController
{
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<ShuttleResponse> register(@RequestBody RegistrationRequest request) {
        return new ResponseEntity<ShuttleResponse>(new ShuttleResponse("User Registered",
                registrationService.register(request)), HttpStatus.OK);
    }

    @GetMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ShuttleResponse> logIn(@RequestParam("email") String email, @RequestParam("password") String password) {
        return new ResponseEntity<ShuttleResponse>(new ShuttleResponse("SignIn Successful",
                registrationService.login(email, password)), HttpStatus.OK);
    }

    @GetMapping(path = "/userdetails")
    public ResponseEntity<ShuttleResponse> getUserDetails(@RequestParam("email") String email) {
        return new ResponseEntity<ShuttleResponse>(new ShuttleResponse("User details fetched",
                registrationService.getUser(email)), HttpStatus.OK);
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<ShuttleResponse> logOut(@RequestParam("email") String email) {
        return new ResponseEntity<>(new ShuttleResponse("SignOut Successful",
                registrationService.logout(email)), HttpStatus.OK);
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<ShuttleResponse> confirmation(@RequestParam("token") String token) {
        return new ResponseEntity<>(new ShuttleResponse("SignOut Successful",
                registrationService.confirmToken(token)), HttpStatus.OK);
    }

    @GetMapping(path = "/generateToken")
    public ResponseEntity<ShuttleResponse> generateToken(){
        return null;
    }
}
