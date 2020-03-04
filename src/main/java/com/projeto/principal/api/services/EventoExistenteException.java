package com.projeto.principal.api.services;

public class EventoExistenteException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4471981354222433520L;

	public EventoExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public EventoExistenteException(String mensagem,Throwable causa) {
		super(mensagem,causa);
	}
}
