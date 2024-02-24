package com.app.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AdminDTO;
import com.app.model.Admin;
import com.app.model.Course;
import com.app.model.Student;
import com.app.service.AdminService;

@RestController
@RequestMapping("/app")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/admin/register")
	public ResponseEntity<String> saveAdminHandler(@RequestBody AdminDTO admindto){
		
		Admin admin = convertAdminDtoToAdmin(admindto);
		String msg = adminService.registerAdmin(admin);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/admin/admit/{studentCode}")
	public ResponseEntity<String> admitStudentHandler(@PathVariable String studentCode){
		
		String msg = adminService.admitStudent(studentCode);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/admin/course")
	public ResponseEntity<String> addCourseHandler(@RequestBody Course course){
		
		String msg = adminService.addCourse(course);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/admin/course/{courseId}/{studentCode}")
	public ResponseEntity<String> assigenCourseToStudentHandler(@PathVariable Long courseId, @PathVariable String studentCode){
		
		String msg = adminService.assigenCourseToStudent(courseId, studentCode);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/course/{courseId}")
	public ResponseEntity<Set<Student>> getStudentsByCourseHandler(@PathVariable Long courseId){
		
		Set<Student> students = adminService.getStudentsByCourse(courseId);
		return new ResponseEntity<Set<Student>>(students, HttpStatus.OK);
	}
	
	@GetMapping("/admin/students/{name}")
	public ResponseEntity<Set<Student>> getStudentByNameHandler(@PathVariable("name") String name){
		
		Set<Student> students = adminService.getStudentByName(name);
		return new ResponseEntity<Set<Student>>(students, HttpStatus.OK);
	}
	
	
	
	public Admin convertAdminDtoToAdmin(AdminDTO admindto) {
		
		Admin admin = new Admin();
		admin.setName(admindto.getName());
		admin.setEmail(admindto.getEmail());
		admin.setPassword(admindto.getPassword());
		admin.setSchoolName(admindto.getSchoolName());
		
		return admin;
		
	}
	

}
