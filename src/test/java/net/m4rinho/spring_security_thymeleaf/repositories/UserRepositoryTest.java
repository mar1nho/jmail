package net.m4rinho.spring_security_thymeleaf.repositories;

import jakarta.persistence.EntityManager;
import net.m4rinho.spring_security_thymeleaf.jmail.dto.JmailDTO;
import net.m4rinho.spring_security_thymeleaf.models.Jmail;
import net.m4rinho.spring_security_thymeleaf.models.Role;
import net.m4rinho.spring_security_thymeleaf.models.User;
import net.m4rinho.spring_security_thymeleaf.web.dto.UserRegistrationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	@DisplayName("Should get the User by his Jmail.")
	void findByJmailSuccessCase1() {
		String jmail = "gustavo@gmail.com";
		UserRegistrationDTO user = new UserRegistrationDTO("Gustavo", "Marinho", jmail, "121199");
		this.createUser(user);
		Optional<User> foundUser = Optional.ofNullable(this.userRepository.findByJmail(jmail));
		assertThat(foundUser.isPresent()).isTrue();
	}
	
	@Test
	@DisplayName("Should not get the user if not exist.")
	void findByJmailSuccessCase2() {
		String jmail = "gustavo@gmail.com";
		Optional<User> foundUser = Optional.ofNullable(this.userRepository.findByJmail(jmail));
		assertThat(foundUser.isEmpty()).isTrue();
	}
	
	private User createUser(UserRegistrationDTO data) {
		User user = new User(data);
		entityManager.persist(user);
		return user;
	}
}