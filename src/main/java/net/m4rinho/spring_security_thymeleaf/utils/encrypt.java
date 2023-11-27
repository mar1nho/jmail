package net.m4rinho.spring_security_thymeleaf.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class encrypt {
	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static String _enc(String password){
		return passwordEncoder.encode(password);
	}
}
