package com.projeto.principal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@Entity
public class CasaShow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message="Campo Casa Show nao pode ficar vazio!!!")
	@Size(max = 50, message="Favor preencher o campo Casa Show com ate 50 caracteres")
	private String nomecasa;	
	
	
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message="Campo Endereço nao pode ficar vazio")
	@Size(max = 50, message="Favor preencher o campo Endereço com no maximo 50 caracteres")
	private String endereco;
	
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "endereco")
	private List <Evento> endereco1;
	
	
	public List<Evento> getEndereco1() {
		return endereco1;
	}
	public void setEndereco1(List<Evento> endereco1) {
		this.endereco1 = endereco1;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomecasa() {
		return nomecasa;
	}
	public void setNomecasa(String nomecasa) {
		this.nomecasa = nomecasa;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CasaShow other = (CasaShow) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
