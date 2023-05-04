package com.kyrxtz.flightservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kyrxtz.flightservices.entities.Airports;

public interface AirportsRepository extends JpaRepository<Airports,Integer> 
{
    @Query("SELECT a.code FROM Airports a")
    List<String> getAllAirportCodes();
}

