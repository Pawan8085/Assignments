package com.student.services;

import java.util.List;

import com.student.exceptions.StudentException;
import com.student.exceptions.SubjectException;
import com.student.models.Student;
import com.student.models.StudentMarks;
import com.student.models.Subject;

public interface StudentService {
	
	/**
	 * 
	 * @param student : student object to register
	 * @return all students records
	 */
	public List<Student> registerStudent(Student student);
	
	/**
	 * 
	 * @param subject : subject obj to register
	 */
	public void addSubjet(Subject subject);
	
	/**
	 * 
	 * @param marks : marks obj to save
	 * @param subjectId : subjectId to get subject obj
	 * @param studentid : studentid to get studnet obj
	 */
	public void addMarks(StudentMarks marks, Long subjectId, Long studentid)throws StudentException, SubjectException;
	
	
	/**
	 * 
	 * @return all students record having marks more than 60
	 */
	public List<Student> getAllStudentWith60Percent();
}
