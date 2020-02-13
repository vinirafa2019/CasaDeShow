package com.projeto.principal.model;

public enum TodosEstilos{

	Axe("Axé"),
	POP("Pop"),
	ROCK("Rock"),
	FUNK("Funk"),
	SERTANEJO("Sertanejo");

	
	
	private String descricao;
	
	TodosEstilos(String descricao) {
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
