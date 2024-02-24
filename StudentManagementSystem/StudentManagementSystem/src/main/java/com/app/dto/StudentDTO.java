package com.app.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentDTO {
	
	private String name;
	private LocalDate dateOfBirth;
	private String gender;
	private String uniqueStudentCode;
	private String email;
	private String password;
	private String mobileNumber;
	private String parentsName;
}
