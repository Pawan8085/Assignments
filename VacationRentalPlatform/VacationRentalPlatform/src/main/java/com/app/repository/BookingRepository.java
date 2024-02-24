package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Booking;
import com.app.entity.Guest;
import com.app.entity.Property;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	Optional<Booking> findByPropertyAndGuest(Property property, Guest guest);
}
