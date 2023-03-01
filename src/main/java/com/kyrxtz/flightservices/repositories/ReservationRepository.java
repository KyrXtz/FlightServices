package com.kyrxtz.flightservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyrxtz.flightservices.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> { }
