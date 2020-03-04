package com.projeto.principal.exceptions;

public class CasaNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2675796780767819569L;

	public CasaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CasaNaoEncontradaException(String mensagem,Throwable causa) {
		super(mensagem,causa);
	}

}
