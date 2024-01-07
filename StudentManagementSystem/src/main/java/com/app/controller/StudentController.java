package com.app.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Course;
import com.app.model.Student;
import com.app.service.StudentService;

@RestController
@RequestMapping("/app")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student/register")
	public ResponseEntity<Student> registerStudentHandler(@RequestBody Student student){
		
		Student savedStudent = studentService.registerStudent(student);
		return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
	}
	
	@PutMapping("/student/update")
	public ResponseEntity<String> updateStudentHandler(@RequestBody Student student){
		
		String msg = studentService.updateStudent(student);
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/student/course")
	public ResponseEntity<Set<Course>> getMyCoursesHandler(){
		
		Set<Course> courses = studentService.getMyCourses();
		return new ResponseEntity<Set<Course>>(courses, HttpStatus.OK);
	}
	
	@DeleteMapping("student/leave/{courseId}")
	public ResponseEntity<String> leaveCourseHandler(@PathVariable Long courseId){
		
		String msg = studentService.leaveCourse(courseId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
