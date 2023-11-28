package net.m4rinho.spring_security_thymeleaf.jmail.services.impl;

import net.m4rinho.spring_security_thymeleaf.jmail.dto.JmailDTO;
import net.m4rinho.spring_security_thymeleaf.jmail.encode.Encoder;
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
import java.util.*;

@Service
public class JmailServiceImpl implements JmailService {
	
	@Autowired
	private UserService userService;
	
	private final JmailRepository jmailRepository;
	
	public JmailServiceImpl(JmailRepository jmailRepository) {
		this.jmailRepository = jmailRepository;
	}
	
	@Override
	public Optional<Jmail> findJmailByUUID(UUID uuid) {
		return jmailRepository.findById(uuid);
	}
	
	@Override
	public void sendJmail(JmailDTO jmailDTO) {
		Jmail jmail = new Jmail();
		BeanUtils.copyProperties(jmailDTO, jmail);
		jmail.setContent(Encoder.encodeContent(jmail.getContent()));
		jmailRepository.save(jmail);
	}
	
	
	@Override
	public List<Jmail> entranceHomeJmails(String jmail) {
		User user = userService.findUserByJmail(jmail);
		if (user != null) {
			List<Jmail> receivedJmails = user.getReceivedEmails();
			receivedJmails.forEach(jmailItem -> {
				String decryptedContent = Encoder.decodeContent(jmailItem.getContent());
				jmailItem.setContent(decryptedContent);
			});
			receivedJmails.sort(Comparator.comparing(Jmail::getTime).reversed());
			return receivedJmails;
		}
		return Collections.emptyList();
	}
	
	@Override
	public void deleteJmailByUUID(UUID uuid) {
		Optional<Jmail> optionalJmail = jmailRepository.findById(uuid);
		optionalJmail.ifPresent(jmail -> {
			User sender = jmail.getSender();
			if (sender != null) {
				sender.getSentEmails().remove(jmail);
			}
			
			User receiver = jmail.getReceiver();
			if (receiver != null) {
				receiver.getReceivedEmails().remove(jmail);
			}
			
			jmail.setSender(null);
			jmail.setReceiver(null);
			
			jmailRepository.delete(jmail);
		});
	}
	
	
}
