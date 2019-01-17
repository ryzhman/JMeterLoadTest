package com.go2it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * For the sake of example the server doesn't provide plenty of functionality
 *
 * @author Alex Ryzhkov
 */
@SpringBootApplication
public class BasicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApiApplication.class, args);
	}
}
