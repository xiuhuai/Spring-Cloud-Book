package com.example.demo;

import com.alibaba.csp.sentinel.Tracer;
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
		@GetMapping(value = "/exception")
		@SentinelResource("exception")
		public String exception() {
			try {
				throw new RuntimeException("Throw RuntimeException ");
			} catch (Throwable throwable) {
				Tracer.trace(throwable);
			}
			return "测试异常比率降级";

		}
	}
}

