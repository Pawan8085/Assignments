package com.app.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class MyErrorDetails {
	private LocalDateTime timestap;
	private String message;
	private String details;
}
