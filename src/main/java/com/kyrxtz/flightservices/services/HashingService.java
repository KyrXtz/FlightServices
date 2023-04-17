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
        int base = 36; 
        int maxDigits = 6;

        long hash = input * base * base;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxDigits; i++) {
            int remainder = (int) (hash % base);
            char c = (char) (remainder < 10 ? '0' + remainder : 'A' + (remainder - 10));
            sb.insert(0, c);
            hash /= base;
        }

        return sb.toString();
    }

    private Integer DecryptHashAlgorithm(String hash) {
        int base = 36;
        int maxDigits = 6;

        long value = 0;
        for (int i = 0; i < maxDigits; i++) {
            char c = hash.charAt(maxDigits - 1 - i);
            int digit = c >= 'A' ? c - 'A' + 10 : c - '0';
            value += digit * Math.pow(base, i);
        }

        return (int) (value / (base * base));
    }
}
