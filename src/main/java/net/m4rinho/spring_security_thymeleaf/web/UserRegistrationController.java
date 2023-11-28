package net.m4rinho.spring_security_thymeleaf.web;

import net.m4rinho.spring_security_thymeleaf.services.UserService;
import net.m4rinho.spring_security_thymeleaf.web.dto.UserRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private final UserService userService;
	
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDTO userRegistrationDto() {
		return new UserRegistrationDTO();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO) {
		if (userService.existsByJmail(registrationDTO.getJmail())){
			return "redirect:/registration?emails_exist";
		}
		userService.save(registrationDTO);
		return "redirect:/login?registered";
	}
}