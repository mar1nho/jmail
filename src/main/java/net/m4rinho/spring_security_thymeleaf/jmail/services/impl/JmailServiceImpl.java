package net.m4rinho.spring_security_thymeleaf.jmail.services.impl;

import net.m4rinho.spring_security_thymeleaf.jmail.dto.JmailDTO;
import net.m4rinho.spring_security_thymeleaf.jmail.repository.JmailRepository;
import net.m4rinho.spring_security_thymeleaf.jmail.services.JmailService;
import net.m4rinho.spring_security_thymeleaf.models.Jmail;
import net.m4rinho.spring_security_thymeleaf.models.Role;
import net.m4rinho.spring_security_thymeleaf.models.User;
import net.m4rinho.spring_security_thymeleaf.repositories.UserRepository;
import net.m4rinho.spring_security_thymeleaf.services.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JmailServiceImpl implements JmailService {
	
	@Autowired
	private UserService userService;
	
	private final JmailRepository jmailRepository;
	
	public JmailServiceImpl(JmailRepository jmailRepository) {
		this.jmailRepository = jmailRepository;
	}
	
	@Override
	public void sendJmail(JmailDTO jmailDTO) {
		Jmail jmail = new Jmail();
		BeanUtils.copyProperties(jmailDTO, jmail);
		jmailRepository.save(jmail);
	}
	
	@Override
	public List<Jmail> entranceHomeJmails(String jmail) {
		Optional<User> user = Optional.ofNullable(userService.findUserByJmail(jmail));
		if (user.isPresent()){
			User _user = user.get();
			List<Jmail> receivedJmails = _user.getReceivedEmails();
			return receivedJmails;
		}
		return user.get().getReceivedEmails();
	}
	
}
