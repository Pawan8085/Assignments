package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.MyOrders;

public interface MyOrdersRepo extends JpaRepository<MyOrders, Long>{

}
