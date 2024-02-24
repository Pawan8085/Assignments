package com.app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentId;
	private String name;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	private String gender;
	private String uniqueStudentCode;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String mobileNumber;
	private String parentsName;
	
	@JsonIgnore
	private String role;
	
	@OneToMany(mappedBy = "student")
	private List<StudentAddress> address;
	
	
	@ManyToMany
	@JsonIgnore
	private Set<Course> courses = new HashSet<>();
	
	public Student() {
		role = "STUDENT";
	}
}
