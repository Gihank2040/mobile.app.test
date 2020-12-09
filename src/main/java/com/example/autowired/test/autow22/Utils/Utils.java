package com.example.autowired.test.autow22.Utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private  final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefgghijklmnopqrstuvwxyz";


    public String generateUserId(int length){
        return generateRandomString(length);

    }

    private String generateRandomString(int length) {
        StringBuilder returnRandomValue = new StringBuilder(length);
        for(int i=0; i<length;i++){
            returnRandomValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnRandomValue);
    }

}
