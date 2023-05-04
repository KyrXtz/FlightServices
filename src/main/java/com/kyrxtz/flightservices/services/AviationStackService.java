package com.kyrxtz.flightservices.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kyrxtz.flightservices.entities.Flight;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@EnableRetry
public class AviationStackService {

    @Value("${aviationstack.apikey}")
    private String apiKey;

    @Retryable(value = {HttpServerErrorException.class, RestClientException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public List<Flight> fetchFlights(int offset) {
        String url = "http://api.aviationstack.com/v1/flights?access_key=" + apiKey + "&offset=" + offset;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject responseObject = new JSONObject(response);
        JSONArray flightsData = responseObject.getJSONArray("data");

        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < flightsData.length(); i++) {
            try {
                JSONObject flightData = flightsData.getJSONObject(i);

                String flightNumber = flightData.getJSONObject("flight").optString("number");
                if (flightNumber == null || flightNumber.isEmpty()) {
                    continue;
                }
                String operatingAirlineIata = flightData.getJSONObject("airline").optString("name");
                if (operatingAirlineIata == null || operatingAirlineIata.isEmpty()) {
                    continue;
                }
                String departureCityIata = flightData.getJSONObject("departure").optString("iata");
                if (departureCityIata == null || departureCityIata.isEmpty()) {
                    continue;
                }
                String arrivalCityIata = flightData.getJSONObject("arrival").optString("iata");
                if (arrivalCityIata == null || arrivalCityIata.isEmpty()) {
                    continue;
                }
                LocalDate dateOfDeparture = LocalDate.parse(flightData.getString("flight_date"));
                OffsetDateTime estimatedDepartureTime = OffsetDateTime.parse(flightData.getJSONObject("departure").getString("scheduled"));

                Flight flight = new Flight();
                flight.setFlightNumber(flightNumber);
                flight.setOperatingAirlines(operatingAirlineIata);
                flight.setDepartureCity(departureCityIata);
                flight.setArrivalCity(arrivalCityIata);
                flight.setDateOfDeparture(Date.valueOf(dateOfDeparture));
                flight.setEstimatedDepartureTime(Timestamp.valueOf(estimatedDepartureTime.toLocalDateTime()));   

                flights.add(flight);
            } catch (Exception e) {
                System.out.println("Error occurred while parsing flight data with index "+ i +" : " + e.getMessage());
                System.out.println("Continuing...");
                continue;
            }
        }
        return flights;
    }

    @Recover
    public List<Flight> recover(HttpServerErrorException ex, int offset) {
        System.err.println("An error occurred while fetching flights for offset " + offset + ": " + ex.getStatusCode() + " " + ex.getStatusText());
        return Collections.emptyList();
    }

    @Recover
    public List<Flight> recover(RestClientException ex, int offset) {
        System.err.println("An error occurred while fetching flights for offset " + offset + ": " + ex.getMessage());
        return Collections.emptyList();
    }
}