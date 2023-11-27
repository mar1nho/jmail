package net.m4rinho.spring_security_thymeleaf.test;

import net.m4rinho.spring_security_thymeleaf.models.User;
import net.m4rinho.spring_security_thymeleaf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
	
	@Autowired
	private UserService userService;
	@Autowired
	@Lazy
	BCryptPasswordEncoder passwordEncoder;
	
	
	//Com isso consigo recuperar dados de tags HTML com a nomeação específica
	@PostMapping("/print")
	public String printToTerminal(@RequestParam("password") String password) {
		
		return "redirect:/index"; // Redireciona para a página inicial após a submissão do formulário
	}
	
}
