package com.kyrxtz.flightservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyrxtz.flightservices.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> { }
