package net.m4rinho.spring_security_thymeleaf.configs;

import net.m4rinho.spring_security_thymeleaf.jmail.services.JmailService;
import net.m4rinho.spring_security_thymeleaf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private JmailService jmailService;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/registration").setViewName("registration");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/index").setViewName("index");
	}
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.authenticationProvider(authenticationProvider())
				.build();
	}
	
	
	@Bean
	protected SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		http
				.securityMatcher("/adm/**")
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().hasRole("ADMIN_ROLE")
				)
				.httpBasic(withDefaults());
		return http.build();
	}
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authenticationProvider(authenticationProvider())
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/", "/registration**", "/js/**", "/css/**", "/img/**","/login**", "/index**")
						.permitAll()
						.anyRequest().authenticated()
						
				)
				.formLogin((form) -> form
						.defaultSuccessUrl("/index")
						.loginPage("/login") // Set login page
						.permitAll()
						.successHandler((request, response, authentication) -> {
							response.sendRedirect("/index");
						})
						.usernameParameter("username")
						.passwordParameter("password")
				)
				.logout((logout) -> logout
						.permitAll()
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout")
						.permitAll());
		return http.build();
	}
	
}