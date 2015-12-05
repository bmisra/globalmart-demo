package com.basab.globalmart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GlobalmartDemoApplication{

	
	private static final Logger log = LoggerFactory.getLogger(GlobalmartDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GlobalmartDemoApplication.class, args);
	}

}
