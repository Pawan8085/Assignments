package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Seller;


public interface SellerRepo extends JpaRepository<Seller, Long>{
	
	Optional<Seller>  findByEmail(String email);
}
