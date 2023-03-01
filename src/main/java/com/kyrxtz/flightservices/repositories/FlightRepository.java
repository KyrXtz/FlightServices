package com.kyrxtz.flightservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyrxtz.flightservices.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight,Integer> { }
