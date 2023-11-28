package net.m4rinho.spring_security_thymeleaf.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.m4rinho.spring_security_thymeleaf.jmail.services.JmailService;
import net.m4rinho.spring_security_thymeleaf.models.Jmail;
import net.m4rinho.spring_security_thymeleaf.models.User;
import net.m4rinho.spring_security_thymeleaf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
	
	
	public boolean canAccessEmail(User user, UUID uuid) {
		Optional<Jmail> data = jmailService.findJmailByUUID(uuid);
		if (data.isPresent()) {
			User receiver = data.get().getReceiver();
			User logged = userService.findLoggedUser();
			return receiver.equals(logged);
		}
		return false;
	}
	
	/*
	@RequestMapping("/emails/{id}")
	public String _emailPage(@PathVariable("id") UUID uuid, Model model) {
		if (canAccessEmail(userService.findLoggedUser(), uuid)) {
			System.err.println("Yes!");
			model.addAttribute("teste", "teste");
			return "index";
		}
		return "redirect:/index";
	}
	*/
	
	@RequestMapping("/delete/{id}")
	public String _emailPage(@PathVariable("id") UUID uuid, Model model) {
		System.err.println(uuid);
		jmailService.deleteJmailByUUID(uuid);
		return "redirect:/index";
		
		
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
		}
		return "index";
	}
}
