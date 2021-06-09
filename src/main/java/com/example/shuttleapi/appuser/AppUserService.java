package com.example.shuttleapi.appuser;

import com.example.shuttleapi.exception.ApiRequestException;
import com.example.shuttleapi.registration.token.ConfirmationToken;
import com.example.shuttleapi.registration.token.ConfirmationTokenService;
import com.example.shuttleapi.utility.OtpGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService
{
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return appUserRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(String .format(
                        USER_NOT_FOUND_MSG, email)));
    }

    public AppUser findByUserEmail(String email)
    {
        return appUserRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(String .format(
                        USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser)
    {
        boolean exists = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();
        if(exists)
            throw new IllegalArgumentException("Email Already taken");

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = new OtpGenerator().generateOtp((int) (Math.random()));
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public boolean signIn(String email, String password)
    {
        AppUser appUser = appUserRepository.getAppUserByEmail(email);

        if(appUser==null)
            throw new IllegalArgumentException("Email not found");
        else if(!appUserRepository.isEnabled(email))
            throw new IllegalArgumentException("Email not confirmed");

        return bCryptPasswordEncoder.matches(password, appUser.getPassword());

    }

    public void lockAppUser(String email)
    {
        appUserRepository.lockAppUser(email);
    }

    public void unlockAppUser(String email)
    {
        appUserRepository.unlockAppUser(email);
    }

    public void enableAppUser(String email)
    {
        appUserRepository.enableAppUser(email);
    }
}
