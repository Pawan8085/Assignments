package com.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyOrders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long myOrderId;
	
	@OneToMany(mappedBy = "myOrders", cascade = CascadeType.ALL)
	private List<Order> orders;
	
	@OneToOne
	private User user;

}
