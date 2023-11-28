package net.m4rinho.spring_security_thymeleaf.web;

import net.m4rinho.spring_security_thymeleaf.jmail.services.JmailService;
import net.m4rinho.spring_security_thymeleaf.models.Jmail;
import net.m4rinho.spring_security_thymeleaf.models.User;
import net.m4rinho.spring_security_thymeleaf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JmailService jmailService;
	
	@GetMapping("/")
	public String home() {
		return "login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/index")
	public String index(Model model) {
		User user = userService.findLoggedUser();
		if (user != null) {
			List<Jmail> list = jmailService.entranceHomeJmails(user.getJmail());
			model.addAttribute("emails", list);
			model.addAttribute("user", userService.findLoggedUser());
			if (list.isEmpty()) {
				model.addAttribute("noemails", true); // Adiciona um atributo indicando a ausência de e-mails
			}
		}
		return "index";
	}
	
	
	
}
