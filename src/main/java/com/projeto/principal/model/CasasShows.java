package com.projeto.principal.model;

public enum CasasShows {

	PENDENTE("Pendente"), 
	RECEBIDO("Recebido");


	
	private String descricao;
	
	CasasShows(String descricao) {
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
