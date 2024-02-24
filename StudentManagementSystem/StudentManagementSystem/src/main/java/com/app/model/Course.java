package com.app.model;

import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseId;
	private String courseName;
	private String description;
	private String courseType;
	private String duration;
	private String topics;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
	
	@ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Student> students = new HashSet<>();
	
	

}
