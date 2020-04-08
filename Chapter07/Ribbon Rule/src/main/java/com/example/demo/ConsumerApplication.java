package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
	/**
	 * @LoadBalanced注解，表示开启客户端负载均衡
	 */
	@LoadBalanced
	/**
	 * 实例化RestTemplate
	 */
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
