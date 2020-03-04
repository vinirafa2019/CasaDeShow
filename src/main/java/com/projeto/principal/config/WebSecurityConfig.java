package com.projeto.principal.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.
		csrf().disable()
		.authorizeRequests()		
		.antMatchers("/api/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/casadeshow").permitAll()
		.antMatchers("/eventos").permitAll()
		.antMatchers("/historico").permitAll()
		.antMatchers("/cadastro").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login").permitAll()	
		.and()
		.logout()
		.logoutSuccessUrl("/login?logout")
		.permitAll();
		
	}
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;		
	}
	@Override
	public void configure(WebSecurity config) throws Exception {
		config.ignoring().antMatchers("/css/**").antMatchers("/js/**").antMatchers("/img/**");
	}
}