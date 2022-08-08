package com.bit.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.bit.blog.dto.ResponseDto;

@RestController
@ControllerAdvice
//31강.

public class GlobalExceptionHandler {
	
//	@ExceptionHandler(value=IllegalArgumentException.class)
//	// IllegalArgumentException 발생할때 마다, "<h1>"+e.getMessage()+"</h1>"; 이게 호출됨.
//	public String handleArgumentException(IllegalArgumentException e) {
//		return "<h1>"+e.getMessage()+"</h1>";
//			
//	}
	
	
	@ExceptionHandler(value=Exception.class) //모든 exception발생할 때 잡을 때.
	// IllegalArgumentException 발생할때 마다, "<h1>"+e.getMessage()+"</h1>"; 이게 호출됨.
	public ResponseDto<String> handleArgumentException(Exception e) {
		System.out.println("-----------------------------------");
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		
			
	}
	

 }











