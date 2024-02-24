package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.Admin;
import com.app.model.Student;
import com.app.repository.AdminRepo;
import com.app.repository.StudentRepo;


@Service
public class CustomerUserDetailService implements UserDetailsService{
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Admin> optAdmin = adminRepo.findByEmail(username);
		if(optAdmin.isPresent()) {
			
			Admin admin = optAdmin.get();
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(admin.getRole()));
			return new User(admin.getEmail(), admin.getPassword(), authorities);
		}
		
		Optional<Student> optStudent = studentRepo.findByEmail(username);
		if(optStudent.isPresent()) {
			
			Student student = optStudent.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(student.getRole()));
			return new User(student.getEmail(), student.getPassword(), authorities);
			
		}
		throw new UsernameNotFoundException("User not found with username: " + username);
	}

}
