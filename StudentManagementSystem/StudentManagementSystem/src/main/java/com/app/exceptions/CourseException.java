package com.app.exceptions;

public class CourseException extends RuntimeException{
	
	public CourseException() {}
	public CourseException(String msg) {
		super(msg);
	}
}
