package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//支持服务发现。
@EnableDiscoveryClient
public class ConsulProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsulProviderApplication.class, args);
	}
	@RestController
	class HelloController {
		@GetMapping(value = "/hello/{string}")
		public String echo(@PathVariable String string) {
			return string;
		}
	}
}
