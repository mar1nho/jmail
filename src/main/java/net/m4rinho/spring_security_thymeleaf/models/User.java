package net.m4rinho.spring_security_thymeleaf.models;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_tb", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements Serializable {
	
	@Serial
	private final static long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String jmail;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	
	@OneToMany(mappedBy = "sender", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private List<Jmail> sentEmails;
	
	@OneToMany(mappedBy = "receiver", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Jmail> receivedEmails;
	
	
	public User(String firstName, String lastName, String jmail, String password, Collection<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmail = jmail;
		this.password = password;
		this.roles = roles;
	}
	
	public User() {
	
	}
	
	public List<Jmail> getSentEmails() {
		return sentEmails;
	}
	
	public void setSentEmails(List<Jmail> sentEmails) {
		this.sentEmails = sentEmails;
	}
	
	public List<Jmail> getReceivedEmails() {
		return receivedEmails;
	}
	
	public void setReceivedEmails(List<Jmail> receivedEmails) {
		this.receivedEmails = receivedEmails;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public void setJmail(String jmail) {
		this.jmail = jmail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
