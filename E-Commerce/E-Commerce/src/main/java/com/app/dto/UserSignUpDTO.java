package com.app.dto;

import com.app.entities.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDTO {
	
	private String name;
	private String location;
	private Gender gender;
	private String phoneNo;
	private String email;
	private String password;
}
