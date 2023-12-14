package com.demo;

import java.util.concurrent.FutureTask;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class BlogPostApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(BlogPostApplication.class, args);
		
		
	
		
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
			
	}

}
