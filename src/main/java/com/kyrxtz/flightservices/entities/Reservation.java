package com.kyrxtz.flightservices.entities;

import jakarta.persistence.Entity;

@Entity
public class Reservation extends AbstractEntity {
    
    private boolean checkedIn;
    private int numberOfBags;


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
