package net.m4rinho.spring_security_thymeleaf.jmail.repository;

import net.m4rinho.spring_security_thymeleaf.models.Jmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JmailRepository extends JpaRepository<Jmail, UUID> {

}
