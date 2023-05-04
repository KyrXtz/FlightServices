package com.kyrxtz.flightservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@EnableScheduling
public class DeleteExpiredFlightsJobService {

    @Autowired
    private FlightDataService flightService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Athens")
    public void deleteExpiredFlights() {
        System.out.println("Starting deleteExpiredFlights Job. Date: "+ LocalDate.now());
        LocalDate yesterday = LocalDate.now().minusDays(1);
        flightService.deleteFlightsBeforeDate(yesterday);
        System.out.println("Succesful finish of deleteExpiredFlights Job.");
    }
}
