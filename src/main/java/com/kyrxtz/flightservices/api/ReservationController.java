package com.kyrxtz.flightservices.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kyrxtz.flightservices.dtos.CreateReservationRequest;
import com.kyrxtz.flightservices.dtos.UpdateReservationRequest;
import com.kyrxtz.flightservices.entities.Flight;
import com.kyrxtz.flightservices.entities.Passenger;
import com.kyrxtz.flightservices.entities.Reservation;
import com.kyrxtz.flightservices.repositories.FlightRepository;
import com.kyrxtz.flightservices.repositories.PassengerRepository;
import com.kyrxtz.flightservices.repositories.ReservationRepository;

@RestController
public class ReservationController {
    
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public List<Flight> getFlights(){
        return flightRepository.findAll();
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    @Transactional
    public Reservation saveReservation(@RequestBody CreateReservationRequest request){

        Flight flight = flightRepository.findById(request.getFlightId()).get();

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setMiddleName(request.getPassengerMiddleName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());

        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        return reservationRepository.save(reservation);
    }

    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.GET)
    public Reservation findReservation(@PathVariable("id") int id){
        return reservationRepository.findById(id).get();
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.PUT)
    public Reservation updateReservation(@RequestBody UpdateReservationRequest request){
        Reservation reservation = reservationRepository.findById(request.getId()).get();
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.isCheckedIn());

        return reservationRepository.save(reservation);
    }
}
