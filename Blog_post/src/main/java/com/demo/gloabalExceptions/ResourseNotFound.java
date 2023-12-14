package com.demo.gloabalExceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResourseNotFound  extends RuntimeException{

	public ResourseNotFound ()
	{
		super();
	}
	
	public ResourseNotFound (String str)
	{
		
	
		super(str);
	}
	
}
