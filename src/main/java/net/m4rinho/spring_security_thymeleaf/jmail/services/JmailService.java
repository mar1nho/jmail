package net.m4rinho.spring_security_thymeleaf.jmail.services;

import net.m4rinho.spring_security_thymeleaf.jmail.dto.JmailDTO;
import net.m4rinho.spring_security_thymeleaf.models.Jmail;
import net.m4rinho.spring_security_thymeleaf.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface JmailService  {
	void deleteAllByUserLogged();
	boolean canAccessEmail(User user, UUID uuid);
	Optional<Jmail> findJmailByUUID(UUID uuid);
	void sendJmail(JmailDTO jmailDTO);
	List<Jmail> entranceHomeJmails(String jmail);
	void deleteJmailByUUID(UUID uuid);
}
