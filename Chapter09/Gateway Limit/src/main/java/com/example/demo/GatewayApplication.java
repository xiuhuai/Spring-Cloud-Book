package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
		@Bean
	public KeyResolver ipKeyResolver() {
		//按IP来限流。
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
	/*@Bean
	public KeyResolver apiKeyResolver() {
		//按URL限流,即以每秒内请求数按URL分组统计。
		return exchange -> Mono.just(exchange.getRequest().getPath().toString());
	}

	@Bean
	public KeyResolver userKeyResolver() {
		//按用户限流。
		return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("u_id"));
	}*/
}
