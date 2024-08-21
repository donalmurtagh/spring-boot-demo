package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		new MissingNullableAnnotations(null);
		SpringApplication.run(DemoApplication.class, args);
	}
}
