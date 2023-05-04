package com.kyrxtz.flightservices.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kyrxtz.flightservices.entities.Flight;

@Component
@EnableScheduling
public class FlightDataUpdateJobService {

    @Autowired
    private AviationStackService aviationStackService;

    @Autowired
    private FlightDataService flightService;

    private int offsetMultiplier = 0;

    @Scheduled(cron = "0 0 03 1 * *", zone = "Europe/Athens")
    public void updateFlightData() {
        System.out.println("Starting updateFlightData Job. Date: "+ LocalDate.now());
        while (offsetMultiplier < 90) {
            System.out.println("Trying for offset: "+ 100 *offsetMultiplier);
            try {
                List<Flight> allFlights = aviationStackService.fetchFlights(100 * offsetMultiplier);
                List<String> airportCodes = flightService.getAirportCodes();

                List<Flight> filteredFlights = allFlights.stream()
                        .filter(flight -> airportCodes.contains(flight.getDepartureCity()) && airportCodes.contains(flight.getArrivalCity()))
                        .collect(Collectors.toList());
                try{
                    flightService.saveFlights(filteredFlights);
                    for (Flight flight : filteredFlights) {
                        addNewFlightEntries(flight, 20);
                    }
                }
                catch(Exception e){
                    System.err.println("An error occurred while generating flights for offset " + (100 * offsetMultiplier) + ": " + e.getMessage());
                }
            }
            catch (Exception e) {
                System.err.println("An error occurred while fetching flights for offset " + (100 * offsetMultiplier) + ": " + e.getMessage());
                offsetMultiplier++;
            }
                         
            offsetMultiplier++;
            
        }
        offsetMultiplier = 0;
        System.out.println("Succesful finish of updateFlightData Job.");
    }

    private void addNewFlightEntries(Flight originalFlight, int numOfEntries) {
        List<Flight> newFlights = new ArrayList<>();
        Random random = new Random();
        LocalDate originalDate = originalFlight.getDateOfDeparture().toLocalDate();
        Timestamp originalEstimatedDepartureTime = originalFlight.getEstimatedDepartureTime();

        for (int i = 0; i < numOfEntries; i++) {
            Flight newFlight = new Flight();
            newFlight.setFlightNumber(originalFlight.getFlightNumber());
            newFlight.setOperatingAirlines(originalFlight.getOperatingAirlines());
            newFlight.setDepartureCity(originalFlight.getDepartureCity());
            newFlight.setArrivalCity(originalFlight.getArrivalCity());

            LocalDate randomDate = originalDate.plusDays(random.nextInt(31));
            newFlight.setDateOfDeparture(Date.valueOf(randomDate));

            if (originalEstimatedDepartureTime != null) {
                LocalDateTime originalLocalDateTime = originalEstimatedDepartureTime.toLocalDateTime();
                LocalDateTime newEstimatedDepartureTime = LocalDateTime.of(randomDate, originalLocalDateTime.toLocalTime());
                newFlight.setEstimatedDepartureTime(Timestamp.valueOf(newEstimatedDepartureTime));
            }

            newFlights.add(newFlight);
        }

        flightService.saveFlights(newFlights);
    }
}