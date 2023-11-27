package net.m4rinho.spring_security_thymeleaf.web;

import net.m4rinho.spring_security_thymeleaf.models.User;
import net.m4rinho.spring_security_thymeleaf.services.UserService;
import net.m4rinho.spring_security_thymeleaf.web.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "login";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/index")
	public String index(Model model){
		User user = userService.findLoggedUser();
		if (user!=null){
			model.addAttribute("user", userService.findLoggedUser());
		}
		return "index";
	}
}
