package com.kyrxtz.flightservices.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Reservation extends AbstractEntity {
    
    private boolean checkedIn;
    private int numberOfBags;
    @OneToOne
    private Flight flight;  
    @OneToOne
    private Passenger passenger;
    @Transient
    private String encryptedId;

    public String getEncryptedId() {
        return encryptedId;
    }
    public void setEncryptedId(String encryptedId) {
        this.encryptedId = encryptedId;
    }
    
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    public boolean isCheckedIn() {
        return checkedIn;
    }
    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
    public int getNumberOfBags() {
        return numberOfBags;
    }
    public void setNumberOfBags(int numberOfBags) {
        this.numberOfBags = numberOfBags;
    }
}
