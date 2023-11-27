package net.m4rinho.spring_security_thymeleaf.jmail.dto;

import net.m4rinho.spring_security_thymeleaf.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class JmailDTO {
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private String content;
	private User sender;
	private User receiver;
	private String title;
	private LocalDateTime time;
	private String resume;
	
	public JmailDTO(String content,String resume, User sender, User receiver, String title, LocalDateTime time) {
		this.content = content;
		this.sender = sender;
		this.resume = resume;
		this.receiver = receiver;
		this.title = title;
		this.time = time;
		//Encode the password before create the Object
		this.sender.setPassword(passwordEncoder.encode(this.sender.getPassword()));
		this.receiver.setPassword(passwordEncoder.encode(this.sender.getPassword()));
	}
	
	public JmailDTO() {
	
	}
	
	public String getResume() {
		return resume;
	}
	
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public User getSender() {
		return sender;
	}
	
	public void setSender(User sender) {
		this.sender = sender;
	}
	
	public User getReceiver() {
		return receiver;
	}
	
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
