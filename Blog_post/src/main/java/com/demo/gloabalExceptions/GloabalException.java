package com.demo.gloabalExceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GloabalException {
	
	@ExceptionHandler(ResourseNotFound.class)
	ResponseEntity<String>passException(ResourseNotFound msg)
	{
		
		return new  ResponseEntity<String>(msg.getMessage(),HttpStatus.NOT_FOUND);
		
		
	}
	
	//MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String,String>> ValidationException(MethodArgumentNotValidException msg)
	{
		
		Map <String ,String> resp=new HashMap<>();
//		msg.getBindingResult().getAllErrors().forEach((err)->{
//			String field=((FieldError)err).getField();
//			String message=err.getDefaultMessage();
		
	List<ObjectError> err=	msg.getBindingResult().getAllErrors();
	
	for(ObjectError e:err)
	{
		String field=((FieldError)e).getField();
		String message=e.getDefaultMessage();
		resp.put(field, message);
	}
//		/HttpRequestMethodNotSupportedException	

		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	ResponseEntity<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException msg)
	{
		Map <String ,String> resp=new HashMap<>();
		
		
		String  str=msg.getMessage();
		
		return new ResponseEntity<String> ("non Comapatable method use proper method with its respective url ",HttpStatus.NOT_ACCEPTABLE); 
	}

}
