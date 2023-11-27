package net.m4rinho.spring_security_thymeleaf.jmail.services;


import net.m4rinho.spring_security_thymeleaf.jmail.dto.JmailDTO;
import net.m4rinho.spring_security_thymeleaf.models.Jmail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface JmailService  {
	Optional<Jmail> findJmailByUUID(UUID uuid);
	void sendJmail(JmailDTO jmailDTO);
	List<Jmail> entranceHomeJmails(String jmail);
}
