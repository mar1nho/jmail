package net.m4rinho.spring_security_thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SpringSecurityThymeleafApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityThymeleafApplication.class, args);
	}
	
}
