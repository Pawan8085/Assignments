package com.app.exceptions;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErroDetails {
	
	private LocalDateTime timestamp;
	private String messagae;
	private String details;
}
