package com.projeto.principal.model;

import java.math.BigDecimal;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Compra {
	
	public Compra() {}
	
	private static final long serialVersion=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Evento even;
	
	public Evento getEven() {
		return even;
	}

	public void setEven(Evento even) {
		this.even = even;
	}

	private int quantidade;
	
	private BigDecimal Valorunitario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorunitario() {
		return Valorunitario;
	}

	public void setValorunitario(BigDecimal valorunitario) {
		Valorunitario = valorunitario;
	}
}
