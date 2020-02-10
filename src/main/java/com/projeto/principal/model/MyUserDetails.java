package com.projeto.principal.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.principal.repository.UserRepository;
@Service
public class MyUserDetails implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	
	  @PersistenceContext
	    private EntityManager manager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user= repo.findByUsername(username);
		
		if(user==null)
			throw new UsernameNotFoundException("Usuario nao pode ser nulo");
		return new UserPrincipal(user);
	}

    public void adicionaUsuario(User usuario){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hasSenha = passwordEncoder.encode(usuario.getpassword());
        usuario.setpassword(hasSenha);        
        manager.persist(usuario);
        }
	
}
