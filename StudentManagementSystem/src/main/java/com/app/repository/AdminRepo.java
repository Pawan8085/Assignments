package com.app.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Admin;
import com.app.model.Student;

public interface AdminRepo extends JpaRepository<Admin, Long>{
	
	Optional<Admin> findByEmail(String email);
	@Query("SELECT s FROM Admin a JOIN a.admitedStudents s " +
		       "WHERE a.email = ?1 AND s.name = ?2")
		Set<Student> findAdmitedStudentsByName(String adminEmail, String studentName);

}
