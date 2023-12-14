package com.demo.dtos;

public class ApiResponse {
	
	String message;
	public ApiResponse() {
		super();
	}
	public ApiResponse(String message, boolean successfully) {
		super();
		this.message = message;
		this.successfully = successfully;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	boolean successfully;
	

}
