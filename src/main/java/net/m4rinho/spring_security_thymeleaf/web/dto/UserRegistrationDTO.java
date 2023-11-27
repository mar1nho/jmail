package net.m4rinho.spring_security_thymeleaf.web.dto;

public class UserRegistrationDTO {
	private String firstName;
	private String lastName;
	private String jmail;
	private String password;
	
	public UserRegistrationDTO(String firstName, String lastName, String jmail, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmail = jmail;
		this.password = password;
	}
	
	public UserRegistrationDTO() {
	
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getJmail() {
		return jmail;
	}
	
	public void setJmail(String email) {
		this.jmail = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
