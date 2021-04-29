package com.example.shuttleapi.registration;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailSenderService
{
    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(SimpleMailMessage email)
    {
        mailSender.send(email);
    }
}
