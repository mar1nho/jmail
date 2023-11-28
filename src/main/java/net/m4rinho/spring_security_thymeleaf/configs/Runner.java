package net.m4rinho.spring_security_thymeleaf.configs;

import net.m4rinho.spring_security_thymeleaf.jmail.dto.JmailDTO;
import net.m4rinho.spring_security_thymeleaf.jmail.services.JmailService;
import net.m4rinho.spring_security_thymeleaf.models.User;
import net.m4rinho.spring_security_thymeleaf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class Runner implements CommandLineRunner {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JmailService jmailService;
	
	@Override
	public void run(String... args) throws Exception {
		saveData();
	}
	
	
	
	public void saveData(){
		String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas placerat tellus at orci ullamcorper ultricies. Nunc faucibus molestie nisl non hendrerit. Vestibulum mollis tincidunt molestie. In pellentesque augue id hendrerit rutrum. Sed tristique egestas purus. Nulla rhoncus, elit quis dignissim viverra, tortor odio pulvinar lacus, vel condimentum purus quam eu sem. Nullam et blandit ante. Etiam vitae convallis justo, lacinia egestas nisi. Proin pellentesque in massa in dignissim.\n" + "\n" +
				"Nunc lobortis euismod arcu non sollicitudin. Quisque molestie molestie pellentesque. Praesent risus libero, efficitur in mauris convallis, dictum vulputate orci. Cras et odio scelerisque, semper magna vel, maximus ligula. Fusce eu sagittis tellus. Suspendisse potenti. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed et ornare tellus.\n" + "\n" +
				"Praesent ligula erat, efficitur vitae quam at, gravida ultricies diam. Praesent nibh lacus, laoreet a congue eget, viverra eget augue. Aenean id orci sapien. Sed non dignissim metus. Sed efficitur, quam non tempor laoreet, diam sem pulvinar nulla, non elementum tellus odio vel ante. Nulla euismod pulvinar mattis. Pellentesque dapibus quam nec felis mollis lacinia. Aliquam varius massa turpis. Aliquam pulvinar imperdiet ex, id tristique mauris euismod sit amet. Mauris at porttitor tellus. Donec vel felis posuere, sagittis arcu varius, faucibus nisl. Nullam eu est non leo faucibus tristique. Curabitur luctus quam ac neque ultrices sagittis. Sed sodales urna ac massa aliquet, in venenatis turpis rutrum.\n" + "\n" +
				"Integer pretium quis libero a consequat. Praesent sit amet leo nulla. Pellentesque orci lacus, pellentesque nec pharetra et, posuere sit amet ex. Pellentesque laoreet pharetra ante a consequat. Ut nibh lacus, maximus in porttitor sit amet, interdum a dui. Etiam diam nibh, facilisis sit amet urna ut, cursus mattis risus. Nullam et aliquam diam. Morbi suscipit magna ac enim consequat, quis euismod orci euismod. Praesent placerat posuere ligula, sit amet elementum risus dignissim a. Vestibulum tempus ac lectus non interdum.\n" + "\n" +
				"Quisque rutrum ac sem imperdiet interdum. Fusce venenatis tellus id sem varius ullamcorper. Vestibulum quis accumsan ante, sit amet placerat urna. Quisque mollis tellus vulputate, sagittis libero quis, dignissim neque. Mauris mattis turpis sed dolor condimentum dictum. Nulla pharetra consectetur diam, sed maximus purus vehicula a. Nunc quis eros mattis, tincidunt justo in, dignissim justo. Phasellus ultrices venenatis enim. Integer eget bibendum orci. Interdum et malesuada fames ac ante ipsum primis in faucibus.";
		String resume = "Aliquam varius massa turpis. Aliquam pulvinar imperdiet ex, id tristique mauris euismod sit amet!";
		String title = "Lorem ipsum";
		User sender = userService.findUserByJmail("gustavo@gmail.com");
		User receiver = userService.findUserByJmail("pedro@gmail.com");
		
		JmailDTO jmailDTO = new JmailDTO(text,resume, receiver, sender, title, LocalDateTime.now());
		JmailDTO jmailDTO2 = new JmailDTO(text,resume, sender, receiver, title, LocalDateTime.now());
		
		for (int i = 0; i < 30; i++){
			jmailService.sendJmail(jmailDTO2);
			jmailService.sendJmail(jmailDTO);
		}
		
	}
}
