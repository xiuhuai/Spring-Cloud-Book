package com.example.demo;

import com.example.demo.custom.MyCustomPing;
import com.netflix.client.ClientFactory;
import com.netflix.loadbalancer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.netflix.loadbalancer.IRule;
@SpringBootApplication
@RibbonClient("service-producer") // 指定目标应用名称
public class ConsumerApplication {

public static void main(String[] args) {
	SpringApplication.run(ConsumerApplication.class, args);
}
	/**
	 * @LoadBalanced注解，开启客户端负载均衡
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
