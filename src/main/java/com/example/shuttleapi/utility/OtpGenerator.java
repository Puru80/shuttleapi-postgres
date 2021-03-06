package com.example.shuttleapi.utility;

import org.springframework.stereotype.Service;

@Service
public class OtpGenerator {

    public String generateOtp(int len){
        int randomPin;
        if(len>=8)
            randomPin = (int) (Math.random()*900000) + 100000;
        else if(len>4)
            randomPin = (int) (Math.random()*90000) + 10000;
        else
            randomPin = (int) (Math.random()*9000) + 1000;
        return String.valueOf(randomPin);
    }

}
