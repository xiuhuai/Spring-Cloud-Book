package com.example.demo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SentinelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelApplication.class, args);
	}

	@RestController
	public class TestController {
		@GetMapping(value = "/hello")
		@SentinelResource("hello")
		public String hello() {
			return "Hello Sentinel Demo";
		}
	}
}
