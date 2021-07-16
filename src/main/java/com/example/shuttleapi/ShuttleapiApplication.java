package com.example.shuttleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(path = "/")
public class ShuttleapiApplication
{

	public static void main(String[] args) {
		System.setProperty("user.timezone", "Asia/Kolkata");
		SpringApplication.run(ShuttleapiApplication.class, args);
	}

	@GetMapping
	public String landingPage()
	{
		return "Hello User";
	}



}
