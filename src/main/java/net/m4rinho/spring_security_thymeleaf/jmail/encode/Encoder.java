package net.m4rinho.spring_security_thymeleaf.jmail.encode;

import java.util.Base64;

public class Encoder {
	public static String encodeContent(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}
	
	public static String decodeContent(String encodedPassword) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
		return new String(decodedBytes);
	}
}
