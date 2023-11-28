package net.m4rinho.spring_security_thymeleaf.jmail.repository;

import net.m4rinho.spring_security_thymeleaf.models.Jmail;
import net.m4rinho.spring_security_thymeleaf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface JmailRepository extends JpaRepository<Jmail, UUID> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Jmail j WHERE j.receiver.id = :receiver")
	void deleteAllByReceiver(Long receiver);
}
