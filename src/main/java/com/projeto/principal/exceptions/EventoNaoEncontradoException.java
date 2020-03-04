package com.projeto.principal.exceptions;

public class EventoNaoEncontradoException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8388114696315109899L;

	public EventoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EventoNaoEncontradoException(String mensagem,Throwable causa) {
		super(mensagem,causa);
	}
}
