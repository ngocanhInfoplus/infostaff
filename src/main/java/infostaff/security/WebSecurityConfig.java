package infostaff.security;

import javax.sql.DataSource;

import infostaff.config.JwtAuthenticationEntryPoint;
import infostaff.config.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import infostaff.service.impl.CustomTokenBasedRememberMeService;
import infostaff.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	private String tokenKey = "infostaff";

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Sét đặt dịch vụ để tìm kiếm User trong Database.
		// Và sét đặt PasswordEncoder.
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.cors();
		// Các trang không yêu cầu login
		http.authorizeRequests().antMatchers("/api/v1.0/infostaff/authenticate").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
		// Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
		// Nếu chưa login, nó sẽ redirect tới trang /login.
		http.authorizeRequests().antMatchers("/api/v1.0/infostaff/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		// Trang chỉ dành cho ADMIN
		http.authorizeRequests().antMatchers("/api/v1.0/infostaff/admin").access("hasRole('ROLE_ADMIN')");

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(rememberMeAuthenticationFilter(), BasicAuthenticationFilter.class);

		// Cấu hình cho Login Form.
//		http.authorizeRequests().and().formLogin()//
//				// Submit URL của trang login
//				.loginProcessingUrl("/api/v1.0/infostaff/validation") // Submit URL
//				.failureUrl("/api/v1.0/infostaff/login?error=true")//
//				.usernameParameter("username")//
//				.passwordParameter("password");

//		http.authorizeRequests().and().httpBasic(); 

		// Cấu hình Remember Me.
//		http.authorizeRequests().and().formLogin().loginProcessingUrl("/api/v1.0/infostaff/login");
//		http.authorizeRequests().and() //
//				.rememberMe().rememberMeParameter("remember-me").tokenRepository(this.persistentTokenRepository()) //
//				.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

	}

	/**
	 * Remember me config
	 */
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(rememberMeAuthenticationProvider());
	}

	@Bean
	public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
		return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeService());
	}

	@Bean
	public CustomTokenBasedRememberMeService tokenBasedRememberMeService() {
		CustomTokenBasedRememberMeService service = new CustomTokenBasedRememberMeService(tokenKey,
				userDetailsService);
		service.setAlwaysRemember(true);
		service.setCookieName("token");
		return service;
	}

	@Bean
	RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
		return new RememberMeAuthenticationProvider(tokenKey);
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

}
