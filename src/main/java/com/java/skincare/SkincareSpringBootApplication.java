package com.java.skincare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class SkincareSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkincareSpringBootApplication.class, args);
	}

}
