package com.app.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Guest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long guestId;
	private String name;
	private String email;
	private String password;
	private Gender gender;
	private LocalDate dateOfBirth;
	private String bio;
	private String role;
	
	@OneToMany(mappedBy = "guest")
	private List<Booking> bookings;
}
