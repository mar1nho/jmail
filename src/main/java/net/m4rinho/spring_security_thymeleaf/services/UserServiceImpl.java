package net.m4rinho.spring_security_thymeleaf.services;

import jakarta.servlet.http.HttpServletRequest;
import net.m4rinho.spring_security_thymeleaf.models.Role;
import net.m4rinho.spring_security_thymeleaf.models.User;
import net.m4rinho.spring_security_thymeleaf.repositories.UserRepository;
import net.m4rinho.spring_security_thymeleaf.web.dto.UserRegistrationDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	
	@Override
	public User findUserByJmail(String jmail) {
		Optional<User> data = Optional.ofNullable(userRepository.findByJmail(jmail));
		return data.orElseGet(User::new);
	}
	
	@Override
	public void save(UserRegistrationDTO registrationDTO) {
		User user = new User(
				registrationDTO.getFirstName(),
				registrationDTO.getLastName(),
				registrationDTO.getJmail(),
				passwordEncoder.encode(registrationDTO.getPassword()),
				List.of(new Role("ROLE_USER")));
		userRepository.save(user);
	}
	
	@Override
	public User findLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String jmail = auth.getName();
		Optional<User> data = Optional.ofNullable(userRepository.findByJmail(jmail));
		return data.orElseGet(() -> new User(null, null, null, null, null));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByJmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid J-Mail or Password!");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getJmail(),
				user.getPassword(),
				mapRolesToAuthorities(user.getRoles())
		);
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
}