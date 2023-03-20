package com.kyrxtz.flightservices.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyrxtz.flightservices.dtos.CreateReservationRequest;
import com.kyrxtz.flightservices.dtos.UpdateReservationRequest;
import com.kyrxtz.flightservices.entities.Airports;
import com.kyrxtz.flightservices.entities.Flight;
import com.kyrxtz.flightservices.entities.Passenger;
import com.kyrxtz.flightservices.entities.Reservation;
import com.kyrxtz.flightservices.repositories.AirportsRepository;
import com.kyrxtz.flightservices.repositories.FlightRepository;
import com.kyrxtz.flightservices.repositories.PassengerRepository;
import com.kyrxtz.flightservices.repositories.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationController {
    
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    AirportsRepository airportsRepository;

    @RequestMapping(value = "/airports", method = RequestMethod.GET)
    public List<Airports> getAirports(){
        return airportsRepository.findAll();
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public List<Flight> getFlights(@RequestParam("from") String from, @RequestParam("to") String to, 
            @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate){
        return flightRepository.findFlights(from, to, departureDate);
    }

    @RequestMapping(value = "/flights/{id}", method = RequestMethod.GET)
    public Flight findFlight(@PathVariable("id") int id) {
        return flightRepository.findById(id).get();
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
