package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {
	
	
	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http)throws Exception{
		
		http.authorizeHttpRequests()
		.requestMatchers( "/app/admin/register", "/app/student/register").permitAll()
		.requestMatchers("/app/admin/signIn", "/app/admin/admit/*", "/app/admin/course", "/app/admin/course/**", "/app/admin/students/*").hasAuthority("ADMIN")
		.requestMatchers("/app/student/signIn", "/app/student/update", "/app/student/course", "/app/student/leave/*").hasAuthority("STUDENT")
		.and()
		.csrf().disable()
		.formLogin()
		.and()
		.httpBasic();
		
		return http.build();
		
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
