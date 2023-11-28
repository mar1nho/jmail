package net.m4rinho.spring_security_thymeleaf.jmail.controllers;

import net.m4rinho.spring_security_thymeleaf.jmail.services.JmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@Controller
public class JmailController {
	
	@Autowired
	private JmailService jmailService;
	
	@RequestMapping("/delete/{id}")
	public String _emailPage(@PathVariable("id") UUID uuid, Model model) {
		jmailService.deleteJmailByUUID(uuid);
		return "redirect:/index";
	}
	
	@RequestMapping("/exclude")
	public String _excludeAllJmails() {
		jmailService.deleteAllByUserLogged();
		return "redirect:/index?excluded";
	}
	
}
