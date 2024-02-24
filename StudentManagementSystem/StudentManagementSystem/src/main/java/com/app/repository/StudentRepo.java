package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

	
	
	@Query("SELECT s FROM Student s WHERE s.uniqueStudentCode = ?1")
	Optional<Student> findByUniqueStudentCode(String studentCode);

	Optional<Student> findByStudentIdAndCoursesCourseId(Long studentId, Long courseId);
	Optional<Student> findByEmail(String email);
	
	@Query("SELECT  s FROM Student s JOIN s.courses c " +
	           "WHERE s.studentId = ?1 AND c.courseId = ?2")
	    Optional<Student> findByStudentIdAndCourseId(Long studentId, Long courseId);
	
	
}
