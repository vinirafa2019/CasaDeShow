package com.projeto.principal.model;

import java.util.List;

public class Compras {

 private static List<Compras> ingresso;

 private int quantidade;
 
 
 
 
 
 
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + quantidade;
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
	Compras other = (Compras) obj;
	if (quantidade != other.quantidade)
		return false;
	return true;
}

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
