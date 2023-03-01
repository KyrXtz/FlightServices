package com.kyrxtz.flightservices.dtos;

public class UpdateReservationRequest {
    
    private int id;
    private boolean checkIn;
    private int numberOfBags;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isCheckedIn() {
        return checkIn;
    }
    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }
    public int getNumberOfBags() {
        return numberOfBags;
    }
    public void setNumberOfBags(int numberOfBags) {
        this.numberOfBags = numberOfBags;
    }
}
