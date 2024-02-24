package com.app.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Host {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long hostId;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private HostStatus hostStatus;
	private String location;
	private PropertyType propertyType;
	private String about;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate hostingSince;
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "host", orphanRemoval = true)
	private List<Property> properties;
	
	
}
