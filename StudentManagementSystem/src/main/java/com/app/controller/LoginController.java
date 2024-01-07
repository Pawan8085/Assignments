package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Admin;
import com.app.model.Student;
import com.app.repository.AdminRepo;
import com.app.repository.StudentRepo;

@RestController
@RequestMapping("/app")
public class LoginController {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@GetMapping("/admin/signIn")
	public ResponseEntity<Admin> signInAdminHandler(Authentication auth){
		
		
		Optional<Admin> optAdmin = adminRepo.findByEmail(auth.getName());
		if(optAdmin.isPresent()) {
			
			return new ResponseEntity<Admin>(optAdmin.get(), HttpStatus.OK);
		}
		
		throw new BadCredentialsException("Invalid username or password");
		
	}
	
	@GetMapping("/student/signIn")
	public ResponseEntity<Student> signInStudentHandler(Authentication auth){

		Optional<Student> optStudent = studentRepo.findByEmail(auth.getName());
		if(optStudent.isPresent()) {
			
			return new ResponseEntity<Student>(optStudent.get(), HttpStatus.OK);
		}
		
		throw new BadCredentialsException("Invalid username or password");
	}

}
