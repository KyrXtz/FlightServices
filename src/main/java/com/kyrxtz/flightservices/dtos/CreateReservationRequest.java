package com.kyrxtz.flightservices.dtos;

public class CreateReservationRequest {
    private int flightId;
    private String passengerFirstName;
    private String passengerLastName;
    private String passengerMiddleName;
    private String passengerEmail;
    private String passengerPhone;
    
    public int getFlightId() {
        return flightId;
    }
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
    public String getPassengerFirstName() {
        return passengerFirstName;
    }
    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }
    public String getPassengerLastName() {
        return passengerLastName;
    }
    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }
    public String getPassengerMiddleName() {
        return passengerMiddleName;
    }
    public void setPassengerMiddleName(String passengerMiddleName) {
        this.passengerMiddleName = passengerMiddleName;
    }
    public String getPassengerEmail() {
        return passengerEmail;
    }
    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }
    public String getPassengerPhone() {
        return passengerPhone;
    }
    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }
}
