package com.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Seller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sellderId;
	
	private String name;
	private String location;
	private Gender gender;
	private String phoneNo;
	@Column(unique = true)
	private String email;
	@JsonProperty
	(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@JsonIgnore
	private String role;
	
	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private List<Category> categories;
}
