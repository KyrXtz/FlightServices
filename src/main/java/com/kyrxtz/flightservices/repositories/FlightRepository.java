package com.kyrxtz.flightservices.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kyrxtz.flightservices.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight,Integer> { 

    @Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
    List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to, @Param("dateOfDeparture") Date departureDate);

    @Query("SELECT f FROM Flight f WHERE f.id NOT IN (SELECT r.flight.id FROM Reservation r)")
    List<Flight> findFlightsWithoutReservations();
}
