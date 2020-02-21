package com.projeto.principal.config;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.principal.model.UserPrincipal;
import com.projeto.principal.model.Usuarios;
import com.projeto.principal.repository.UserRepository;

@Service
@Transactional 
public class MyUserDetails implements UserDetailsService {
	

	@Autowired
	private UserRepository users;
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuarios user= users.findByUsername(username);

		if(user==null) {
			throw new UsernameNotFoundException("Usuario nao pode ser nulo");
			}				
		if(user.isAdmin()==true){
			return new User(user.getusername(),user.getpassword(),true,true,true,true,user.getAuthorities());
		}else {			
			return new UserPrincipal(user);
		}		
	}
}
