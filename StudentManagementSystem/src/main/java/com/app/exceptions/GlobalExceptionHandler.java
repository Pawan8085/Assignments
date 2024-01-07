package com.app.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.app.model.Student;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroDetails> exceptionHander(Exception e, WebRequest req){
		
		ErroDetails err = new ErroDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessagae(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErroDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErroDetails> NoHandlerFoundExceptionHander(NoHandlerFoundException e, WebRequest req){
		
		ErroDetails err = new ErroDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessagae(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErroDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CourseException.class)
	public ResponseEntity<ErroDetails> courseExceptionHander(CourseException e, WebRequest req){
		
		ErroDetails err = new ErroDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessagae(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErroDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StudentException.class)
	public ResponseEntity<ErroDetails> studentExceptionHander(StudentException e, WebRequest req){
		
		ErroDetails err = new ErroDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessagae(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErroDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErroDetails> adminExceptionHander(AdminException e, WebRequest req){
		
		ErroDetails err = new ErroDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessagae(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErroDetails>(err, HttpStatus.BAD_REQUEST);
	}
	

}
