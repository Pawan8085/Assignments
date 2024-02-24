package com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CartItem;
import com.app.entities.User;

public interface CartRepo extends JpaRepository<CartItem, Long>{

	Page<CartItem> findByUser(User user, Pageable pageable);
}
