package com.projeto.principal.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Descrição não pode ser nulo")
	@Size(max = 60,message="A descricao nao pode conter mais que 60 caracteres")
	private String descricao;
	
	
	private float capacidade;
	
	@NotNull(message = "Data não pode ser nulo")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@NotNull(message = "Valor não pode ser nulo")
	@DecimalMin(value="0.01", message="valor nao pode ser menor que 0,01")
	@DecimalMax(value ="9999999999.99",message="Valor no pode ser mais que 9.999.999.999,99 ")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal Valoringresso;
	
	@Enumerated(EnumType.STRING)
	private CasasShows nomes;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(float capacidade) {
		this.capacidade = capacidade;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getValoringresso() {
		return Valoringresso;
	}
	public void setValoringresso(BigDecimal valoringresso) {
		Valoringresso = valoringresso;
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}





