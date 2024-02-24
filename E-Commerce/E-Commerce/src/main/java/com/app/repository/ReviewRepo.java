package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Review;

public interface ReviewRepo extends JpaRepository<Review, Long>{

}
