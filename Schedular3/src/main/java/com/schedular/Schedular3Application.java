package com.schedular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Schedular3Application {

	public static void main(String[] args) {
		SpringApplication.run(Schedular3Application.class, args);
	}

}
