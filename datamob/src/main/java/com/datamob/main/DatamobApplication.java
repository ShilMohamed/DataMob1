package com.datamob.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DatamobApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatamobApplication.class, args);
	}
}
