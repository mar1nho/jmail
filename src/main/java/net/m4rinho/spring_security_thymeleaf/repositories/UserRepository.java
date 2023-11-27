package net.m4rinho.spring_security_thymeleaf.repositories;

import net.m4rinho.spring_security_thymeleaf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByJmail(String jmail);
}
