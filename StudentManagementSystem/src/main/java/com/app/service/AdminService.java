package com.app.service;


import java.util.Set;

import com.app.exceptions.AdminException;
import com.app.exceptions.CourseException;
import com.app.exceptions.StudentException;
import com.app.model.Admin;
import com.app.model.Course;
import com.app.model.Student;

public interface AdminService {
	
	/**
	 * 
	 * @param admin -> Admin object for registration
	 * @return -> saved admin obj
	 */
	Admin registerAdmin(Admin admin);
	
	/**
	 * 
	 * @param studentCode -> studentCode to find Student obj
	 * @return -> confirmation message 
	 * @throws StudentException
	 */
	String admitStudent(String studentCode)throws StudentException;
	
	/**
	 * 
	 * @param course -> course obj to add course
	 * @return -> confirmation message 
	 * @throws AdminException
	 */
	String addCourse(Course course)throws AdminException;
	
	/**
	 * 
	 * @param courseId -> courseId to find Course obj
	 * @param studentCode -> studentCode to find Student obj
	 * @return -> confirmation message 
	 * @throws CourseException
	 * @throws StudentException
	 */
	String assigenCourseToStudent(Long courseId, String studentCode)throws CourseException, StudentException;
	
	/**
	 * 
	 * @param name -> student name to find student
	 * @return -> set students 
	 * @throws StudentException
	 */
	Set<Student> getStudentByName(String name)throws StudentException;
	
	/**
	 * 
	 * @param courseId -> course Id to find course
	 * @return -> set of courses
	 * @throws CourseException
	 * @throws AdminException
	 */
	Set<Student> getStudentsByCourse(Long courseId)throws CourseException, AdminException;
}
