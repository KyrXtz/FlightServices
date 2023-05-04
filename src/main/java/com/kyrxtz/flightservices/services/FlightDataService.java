package com.kyrxtz.flightservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyrxtz.flightservices.entities.Flight;
import com.kyrxtz.flightservices.repositories.AirportsRepository;
import com.kyrxtz.flightservices.repositories.FlightRepository;

import jakarta.transaction.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class FlightDataService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportsRepository airportRepository;

    public void saveFlights(List<Flight> flights) {
        flightRepository.saveAll(flights);
    }

    @Transactional
    public void deleteFlightsBeforeDate(LocalDate date) {
        Date sqlDate = Date.valueOf(date);
        flightRepository.deleteByDateOfDepartureBefore(sqlDate);
    }

    public List<String> getAirportCodes() {
        return airportRepository.getAllAirportCodes();
    }
}