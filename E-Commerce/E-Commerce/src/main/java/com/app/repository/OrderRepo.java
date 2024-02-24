package com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.MyOrders;
import com.app.entities.Order;
import com.app.entities.User;

public interface OrderRepo extends JpaRepository<Order, Long>{
	
	Page<Order> findByMyOrders(MyOrders myOrders, Pageable pageable);
}
