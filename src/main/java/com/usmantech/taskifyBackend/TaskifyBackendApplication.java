package com.usmantech.taskifyBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskifyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskifyBackendApplication.class, args);
		System.out.println("API Started...");
	}

}
