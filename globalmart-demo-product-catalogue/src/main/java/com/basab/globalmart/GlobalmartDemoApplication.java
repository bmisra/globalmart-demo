package com.basab.globalmart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class GlobalmartDemoApplication implements CommandLineRunner{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(GlobalmartDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GlobalmartDemoApplication.class, args);
	}

	public void run(String... strings) throws Exception {
		//TODO: Replace with logback logger
		log.info("Creating H2 In Memory database ");
		jdbcTemplate.execute("DROP SCHEMA GMDB IF EXISTS");
		jdbcTemplate.execute("CREATE SCHEMA GMDB");
		jdbcTemplate
				.execute("CREATE TABLE GMDB.PRODUCT (PRODUCT_ID LONG NULL, PRODUCT_NAME VARCHAR(50) NOT NULL, PRODUCT_TYPE VARCHAR(50) NOT NULL, PRODUCT_PRICE DOUBLE , PRIMARY KEY (PRODUCT_ID))");
	}

}
