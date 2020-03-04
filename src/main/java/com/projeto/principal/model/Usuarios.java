package com.projeto.principal.model;




import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuarios implements UserDetails , Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message ="Senha nao pode ser nulo")
	private String password;
    @NotEmpty(message ="Nome nao pode ser nulo")
    private String username;
    
    private boolean admin;
    
    public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }
    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return  Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}	

