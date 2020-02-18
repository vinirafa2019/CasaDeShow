package com.projeto.principal.model;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;




import com.projeto.principal.repository.UserRepository;
@Repository
public class MyUserDetails implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	

	
	//@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuarios user= repo.findByUsername(username);

		if(user==null) {
			throw new UsernameNotFoundException("Usuario nao pode ser nulo");
			}else {
				repo.save(user);
			}
		return loadUserByUsername(user.getpassword()+user.getusername());
	}
}
