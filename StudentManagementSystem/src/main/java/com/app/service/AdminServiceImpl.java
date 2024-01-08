package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.exceptions.AdminException;
import com.app.exceptions.CourseException;
import com.app.exceptions.StudentException;
import com.app.model.Admin;
import com.app.model.Course;
import com.app.model.Student;
import com.app.repository.AdminRepo;
import com.app.repository.CourseRepo;
import com.app.repository.StudentRepo;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String registerAdmin(Admin admin) {
		
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		 adminRepo.save(admin);
		 return "You have registered successfully";
	}

	@Override
	public String admitStudent(String studentCode) throws StudentException {
		
		// get current admin details using Authentication obj
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		 Optional<Student> optStudent = studentRepo.findByUniqueStudentCode(studentCode);
		 if(optStudent.isPresent()) {
			 
			Optional<Admin> optAdmin = adminRepo.findByEmail(auth.getName());
			// note! we are not checking Admin isPresent or not cause this method will only get call for authorized admin means will always get an admin
			Admin admin = optAdmin.get();
			Student student = optStudent.get();
			
			admin.getAdmitedStudents().add(student);
			adminRepo.save(admin);
			
			return "One student has admited to school with student code : " + studentCode;
			
		 }
		 throw new StudentException("Invalid student code : "+studentCode);	   
		    
	}

	@Override
	public String addCourse(Course course)  {

		// get current admin details using Authentication obj
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Admin> optAdmin = adminRepo.findByEmail(auth.getName());
		// note! we are not checking Admin isPresent or not cause this method will only get call for authorized admin means will always get an admin
		Admin admin = optAdmin.get();
		
		course.setAdmin(admin);
		admin.getCourses().add(course);
		
		adminRepo.save(admin);
		
		return "Course added successfully";
				 
		
	}

	@Override
	public String assigenCourseToStudent(Long courseId, String studentCode) throws CourseException, StudentException {
		
		
		Optional<Course> optCourse = courseRepo.findById(courseId);
		if(optCourse.isPresent()) {
			
			Optional<Student> optStudent = studentRepo.findByUniqueStudentCode(studentCode);
			System.out.println(optStudent.isPresent());
			if(optStudent.isPresent()) {
				
				Course course = optCourse.get();
				Student student = optStudent.get();
				student.getCourses().add(course);
				course.getStudents().add(student);
				
				courseRepo.save(course);
				
				return "Course assigened successfully to student with student code : "+studentCode;
				
			}
			
			throw new StudentException("Invalid student code : "+studentCode);
			
		}
		throw new CourseException("Invalid course Id : "+courseId);
	}

	@Override
	public Set<Student> getStudentByName(String name) throws StudentException {
		
		// get current admin details using Authentication obj
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
	
		
		Set<Student> students = adminRepo.findAdmitedStudentsByName(auth.getName(), name);
		if(students.size() > 0) {
			
			return students;
		}
		
		throw new StudentException("Student not found");
		
	}

	@Override
	public Set<Student> getStudentsByCourse(Long courseId) throws CourseException, AdminException {

		// get current admin details using Authentication obj
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Admin> optAdmin = adminRepo.findByEmail(auth.getName());
		// note! we are not checking Admin isPresent or not cause this method will only get call for authorized admin means will always get an admin
		Admin admin = optAdmin.get();
		
		
		Optional<Course> optCourse = courseRepo.findById(courseId);
		if(optCourse.isPresent()) {
			
			Course course = optCourse.get();
			
			// check if  course id belongs from current admin or not
			if(course.getAdmin().getAdminId() != admin.getAdminId()) {
				throw new AdminException("This course Id doesn't belong from your course!");
			}
			
			return course.getStudents();
			
		}
		
		throw new CourseException("Invalid course id : "+courseId);
	}
	
	

}
