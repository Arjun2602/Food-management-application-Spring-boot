package com.example.food_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class FoodManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodManagementSystemApplication.class, args);
	}

}
