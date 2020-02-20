package com.projeto.principal.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.principal.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {
	

	@Autowired
	private UserRepository users;
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuarios user= users.findByUsername(username);

		if(user==null) {
			throw new UsernameNotFoundException("Usuario nao pode ser nulo");
			}
		return new User(user.getusername(),user.getpassword(),true,true,true,true,user.getAuthorities());
	}
}
