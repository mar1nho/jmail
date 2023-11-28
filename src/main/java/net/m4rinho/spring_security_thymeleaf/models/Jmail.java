package net.m4rinho.spring_security_thymeleaf.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "jmail")
public class Jmail implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID uuid;
	
	
	private String title;
	
	@DateTimeFormat(pattern="dd-MMM-yyyy HH:mm")
	private LocalDateTime time;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "resume")
	private String resume;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private User receiver;
	
	
	
	public Jmail(String title, String resume, LocalDateTime time, String content, User sender, User receiver) {
		this.resume = resume;
		this.title = title;
		this.time = time;
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public Jmail() {
	
	}
	
	public String getResume() {
		return resume;
	}
	
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public String getContent() {
		return content;
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

}
