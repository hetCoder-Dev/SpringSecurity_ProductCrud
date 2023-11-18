package com.spring.prod.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PlainTextPaaswordEncoder.getInstance();
	}

	@Bean
	public UserDetailsService detailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(detailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		http.authorizeRequests()
		.antMatchers("/home**").hasAnyAuthority("BUYER", "SELLER")
		.antMatchers("/seller/**").hasAuthority("SELLER")
		.antMatchers("/buyer/**").hasAuthority("BUYER")
		.antMatchers("/register").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.failureUrl("/login?error")
		.usernameParameter("email")
		.passwordParameter("password")
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/login")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
	}

}