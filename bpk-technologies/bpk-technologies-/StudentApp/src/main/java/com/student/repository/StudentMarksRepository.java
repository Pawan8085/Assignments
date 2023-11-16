package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.student.models.Student;
import com.student.models.StudentMarks;

public interface StudentMarksRepository extends JpaRepository<StudentMarks, Long> {
	
	 @Query("SELECT sm.student FROM StudentMarks sm WHERE sm.marks > 60")
	    List<Student> findStudentsWithMarksGreaterThan60();
}