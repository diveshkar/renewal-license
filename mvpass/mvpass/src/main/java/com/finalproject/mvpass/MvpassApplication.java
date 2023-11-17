package com.finalproject.mvpass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.finalproject.mvpass")
public class MvpassApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvpassApplication.class, args);
	}

}
