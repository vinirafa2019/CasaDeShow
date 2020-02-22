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
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Evento even;
	
	public Evento getEven() {
		return even;
	}

	private float Valortotal;
	
	public float getValortotal() {
		return Valortotal;
	}

	public void setValortotal(float valortotal) {
		Valortotal = valortotal;
	}

	public void setEven(Evento even) {
		this.even = even;
	}

	private int quantidade = 0;
	
	private float Valorunitario;
	
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

	public float getValorunitario() {
		return Valorunitario;
	}

	public void setValorunitario(float valorunitario) {
		Valorunitario = valorunitario;
	}
}
