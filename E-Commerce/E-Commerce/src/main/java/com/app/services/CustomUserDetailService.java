package com.app.services;

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

import com.app.entities.Seller;
import com.app.repository.SellerRepo;
import com.app.repository.UserRepo;



@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private SellerRepo sellerRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
			
		Optional<Seller> optSeller = sellerRepo.findByEmail(username);
		if(optSeller.isPresent()) {
			
			Seller seller = optSeller.get();
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(seller.getRole()));
			
			return new User(seller.getEmail(), seller.getPassword(), authorities);
			
			
		}
		
		
		Optional<com.app.entities.User> optUser = userRepo.findByEmail(username);
		if(optUser.isPresent()) {
			
			com.app.entities.User user = optUser.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
			
			return new User(user.getEmail(), user.getPassword(), authorities);
			
			
		}
		throw new UsernameNotFoundException("Invalid  User Name!");
		
	}

}
