package com.kyrxtz.flightservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyrxtz.flightservices.entities.Airports;

public interface AirportsRepository extends JpaRepository<Airports,Integer> { }

