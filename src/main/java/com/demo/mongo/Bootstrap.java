package com.demo.mongo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Bootstrap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
				new FileSystemXmlApplicationContext(Bootstrap.class.getResource("/").toString()+"/spring.xml");
	
	}

}
