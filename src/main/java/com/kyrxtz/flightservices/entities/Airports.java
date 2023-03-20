package com.kyrxtz.flightservices.entities;

import jakarta.persistence.Entity;

@Entity
public class Airports extends AbstractEntity{
    private String name;
    private String country;
    private String code;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
