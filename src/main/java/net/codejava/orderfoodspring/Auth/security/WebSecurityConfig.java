package net.codejava.orderfoodspring.Auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import net.codejava.orderfoodspring.Auth.security.cors.CorsFilter;
import net.codejava.orderfoodspring.Auth.security.jwt.AuthEntryPointJwt;
import net.codejava.orderfoodspring.Auth.security.jwt.AuthTokenFilter;
import net.codejava.orderfoodspring.Auth.security.services.UserDetailsServiceImpl;

// @EnableWebSecurity cho phép Spring tìm và tự động áp dụng lớp cho Bảo mật Web toàn cuc.
// @EnableGlobalMethodSecurity cung cấp bảo mật AOP trên các phương thức.
//  Nó cho phép @PreAuthorize, @PostAuthorize, nó cũng hỗ trợ JSR-250. 
// Bạn có thể tìm thêm các tham số trong cấu hình trong Biểu thức bảo mật phương pháp.
/*
ghi đè phương thức config (HttpSecurity http) từ giao diện WebSecurityConfigurerAdapter. 
Nó cho Spring Security biết cách chúng tôi định cấu hình CORS và CSRF,
khi nào chúng ta muốn yêu cầu tất cả người dùng phải được xác thực hay không, 
bộ lọc nào (AuthTokenFilter) và khi nào chúng ta muốn nó hoạt động (lọc trước UsernamePasswordAuthenticationFilter), 
Bộ xử lý ngoại lệ nào được chọn (AuthEntryPointJwt).

Spring Security sẽ tải thông tin chi tiết về Người dùng để thực hiện xác thực và ủy quyền. 
Vì vậy, nó có giao diện UserDetailsService mà chúng ta cần triển khai.

Việc triển khai UserDetailsService sẽ được sử dụng để cấu hình 
DaoAuthenticationProvider bằng phương thức AuthenticationManagerBuilder.userDetailsService ().

Chúng tôi cũng cần một PasswordEncoder cho DaoAuthenticationProvider. 
Nếu chúng tôi không chỉ định, nó sẽ sử dụng văn bản thuần túy.
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	public static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/META-INF/resources/", "classpath:/resources/",
		"classpath:/static/", "classpath:/public/" };


	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CorsFilter corsFilter() {
		CorsFilter filter = new CorsFilter();
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().addFilterBefore(corsFilter(), SessionManagementFilter.class) // adds your custom
																							// CorsFilter
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/api/test/**").permitAll()
				.antMatchers("/comments/**").permitAll()
				.antMatchers("/foods/**").permitAll()
				.antMatchers("/orders/**").permitAll()
				.antMatchers("/types/**").permitAll()
				.antMatchers("/users/**").permitAll()
				.antMatchers("/user/**").permitAll()
				.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}