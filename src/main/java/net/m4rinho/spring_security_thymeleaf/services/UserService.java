package net.m4rinho.spring_security_thymeleaf.services;

import net.m4rinho.spring_security_thymeleaf.models.User;
import net.m4rinho.spring_security_thymeleaf.web.dto.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService extends UserDetailsService {
	User findUserByJmail(String jmail);
	void save(UserRegistrationDTO registrationDTO);
	User findLoggedUser();
}
