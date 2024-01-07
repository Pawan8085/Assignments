package com.app.service;

import java.util.Set;

import com.app.exceptions.CourseException;
import com.app.exceptions.StudentException;
import com.app.model.Course;
import com.app.model.Student;

public interface StudentService {
	
	/**
	 * 
	 * @param student -> student obj to save student
	 * @return -> saved student
	 */
	Student registerStudent(Student student);
	
	/**
	 * 
	 * @param student -> student obj to update student
	 * @return -> return updation confirmation message 
	 * @throws StudentException
	 */
	String updateStudent(Student student)throws StudentException;
	
	/**
	 * 
	 * @return -> set of courses
	 * @throws StudentException
	 */
	Set<Course> getMyCourses()throws StudentException;
	
	/**
	 * 
	 * @param courseId -> to find course
	 * @return -> deletion confirmation message
	 * @throws CourseException
	 * @throws StudentException
	 */
	String leaveCourse(Long courseId)throws CourseException, StudentException;
}
