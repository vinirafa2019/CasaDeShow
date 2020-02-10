package com.projeto.principal.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.
		csrf().disable()
		.authorizeRequests()
		
		.antMatchers("/home").hasAnyRole("home")
		.antMatchers("/casadeshow").hasAnyRole("casashow")
		.antMatchers("/eventos").hasAnyRole("eventos")
		.antMatchers("/historico").hasAnyRole("historico")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login").permitAll()	
		.and()
		.logout()
		.logoutSuccessUrl("/login?logout")
		.permitAll();
		
	}
	@Override
	public void configure(WebSecurity config) throws Exception {
		config.ignoring().antMatchers("/css/**").antMatchers("/js/**");
	}
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     
        auth.inMemoryAuthentication()
                .withUser("vinicius").password("{noop}").roles("home","casashow","eventos")
                .and()
                .withUser("rafa").password("{noop}").roles("home","casashow","eventos","historico");

    }
}
