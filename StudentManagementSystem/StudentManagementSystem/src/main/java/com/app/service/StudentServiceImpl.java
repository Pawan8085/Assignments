package com.app.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.exceptions.CourseException;
import com.app.exceptions.StudentException;
import com.app.model.Course;
import com.app.model.Student;
import com.app.repository.CourseRepo;
import com.app.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public String registerStudent(Student student) {
		
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		 studentRepo.save(student);
		 
		 return "You have registered successfully";
		
	}

	@Override
	public String updateStudent(Student student) throws StudentException{
		
		// handle null studentId
		if(student.getStudentId() == null) {
			throw new StudentException("Don't forget to pass student Id");
		}
		
		Optional<Student> optStudent = studentRepo.findById(student.getStudentId());
		if(optStudent.isEmpty()) {
			throw new StudentException("You have passed invalid Id : "+ student.getStudentId()) ;
		}
		
		// get current student details using Authentication obj
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
		 optStudent = studentRepo.findByEmail(auth.getName());
		// note! we are not checking Student isPresent or not cause this method will only get call for authorized student means will always get student obj
		Student st = optStudent.get();
		
		// check if student id is equal to current student id
		if(student.getStudentId() != st.getStudentId()) {
			
			throw new StudentException("This is not your student Id!");
		}
		
		studentRepo.save(student);
		return "Student details updated successfully";
	}

	@Override
	public Set<Course> getMyCourses() {
		
		// get current student details using Authentication obj
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
						
		Optional<Student> optStudent = studentRepo.findByEmail(auth.getName());
		// note! we are not checking Student isPresent or not cause this method will only get call for authorized student means will always get student obj
		Student student = optStudent.get();
		
		if(student.getCourses().size() > 0) {
			return student.getCourses();
		}
		
		throw new StudentException("You don't have any course!");
	}

	@Override
	public String leaveCourse(Long courseId) throws CourseException, StudentException {
		
		
		Optional<Course> optCourse = courseRepo.findById(courseId);
		if(optCourse.isEmpty()) {
			throw new CourseException("Invalid course Id : "+courseId);
		}
		
		// get current student details using Authentication obj
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
								
		Optional<Student> optStudent = studentRepo.findByEmail(auth.getName());
		// note! we are not checking Student isPresent or not cause this method will only get call for authorized student means will always get student obj
		Student student = optStudent.get();
		
		Optional<Student> optStudent2 = studentRepo.findByStudentIdAndCourseId(student.getStudentId(), courseId);
		
		if(optStudent2.isPresent()) {
			
			Course course = optCourse.get();
			course.getStudents().remove(student);
			student.getCourses().remove(course);
			
			courseRepo.save(course);
			
			return "course removed sucessfully";
			
			
		}
		
		throw new CourseException("This course Id does not belong from your profile");
		
		
	}

	

}
