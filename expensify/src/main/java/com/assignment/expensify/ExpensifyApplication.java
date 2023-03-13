package com.assignment.expensify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class ExpensifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensifyApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
