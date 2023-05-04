package com.kyrxtz.flightservices.services;

import org.springframework.stereotype.Service;

@Service
public class HashingService {
    public String Encrypt(Integer input){
        return HashAlgorithm(input);
    }
    public Integer Decrypt(String input){
        return DecryptHashAlgorithm(input);
    }

    private String HashAlgorithm(Integer input) {
        int row = input + 1;
        
        int value = (int) (Math.pow(row, 3) + Math.pow(row, 2) + Math.pow(row, 1) + 1);
        String hexString = Integer.toHexString(value).toUpperCase();
        return hexString.replaceFirst("^0+(?!$)", "");
    }

    private Integer DecryptHashAlgorithm(String hash) {
        int value = Integer.parseInt(hash, 16);
        int row = 0;

        while (true) {
            int currentValue = (int) (Math.pow(row, 3) + Math.pow(row, 2) + Math.pow(row, 1) + 1);
            if (currentValue == value) {
                return row - 1;
            } else if (currentValue > value) {
                return null;
            }
            row++;
        }
    }
}
