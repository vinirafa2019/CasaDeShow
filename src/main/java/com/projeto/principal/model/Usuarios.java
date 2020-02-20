package com.projeto.principal.model;




import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Usuarios {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message ="Senha nao pode ser nulo")
	private String password;
    @NotEmpty(message ="Nome nao pode ser nulo")
    private String username;
    
    @ManyToMany
    @JoinTable(name = "usuarios_role", joinColumns = @JoinColumn(name="cadastrousuario",referencedColumnName = "usernmae"),
    inverseJoinColumns = )
    private List<Role>roles;
    
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
}	

