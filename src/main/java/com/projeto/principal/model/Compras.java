package com.projeto.principal.model;

import java.util.List;

public class Compras {

 private static List<Compras> ingresso;

 private int quantidade;
 
 
 
 
 
 
public int getQuantidade() {
	return quantidade;
}

public void setQuantidade(int quantidade) {
	this.quantidade = quantidade;
}

public static List<Compras> getIngresso() {
	return ingresso;
}

public static void setIngresso(List<Compras> ingresso) {
	Compras.ingresso = ingresso;
}


}
