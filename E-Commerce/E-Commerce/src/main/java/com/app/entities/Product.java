package com.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private double price;
	private String name;
	private String specifications;
	private String subCategory;
	private String type;
	private long numberOfStocks;
	private long sold;
	private boolean isOutOfStcok;
	private double rating;
	private long ratingCount;
	private long ratingSum;
	
	@JsonIgnore
	@ManyToOne
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Review> reviews;
	
}
