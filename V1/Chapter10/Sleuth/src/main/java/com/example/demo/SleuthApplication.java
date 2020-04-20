package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthApplication.class, args);
	}
	private static Logger log = LoggerFactory.getLogger(SleuthApplication.class);

@GetMapping("/hello")
	public String home() {
		log.info("处理 hello页");
		return "Hello World";
	}
}
