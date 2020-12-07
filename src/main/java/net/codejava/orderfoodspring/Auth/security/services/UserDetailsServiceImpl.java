package net.codejava.orderfoodspring.Auth.security.services;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import net.codejava.orderfoodspring.Auth.models.User;
import net.codejava.orderfoodspring.Auth.payload.request.UpdateUserRequest;
import net.codejava.orderfoodspring.Auth.repository.UserRepository;
import net.codejava.orderfoodspring.Auth.security.WebSecurityConfig;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String tendn) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(tendn)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + tendn));

		return UserDetailsImpl.build(user);
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/**")
	        .addResourceLocations(WebSecurityConfig.CLASSPATH_RESOURCE_LOCATIONS);
	}

	public void save(User user){
        userRepository.save(user);
	}
	
	public List<User> listAll(){
        return userRepository.findAll();
	}
	
	public Optional<User> getbyname(String username){
		return userRepository.findByUsername(username);
	}
}
