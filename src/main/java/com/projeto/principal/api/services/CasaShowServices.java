package com.projeto.principal.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projeto.principal.exceptions.CasaNaoEncontradaException;
import com.projeto.principal.model.CasaShow;
import com.projeto.principal.repository.CasasShowsRep;



@Service
public class CasaShowServices {

	@Autowired
	private CasasShowsRep casaRep;
	
	
	public List<CasaShow> listar() {
		return casaRep.findAll();
	}
	public Optional<CasaShow> buscar(Long id) {
		Optional<CasaShow> casa = casaRep.findById(id);

		if (casa.isEmpty()) {
			throw new CasaNaoEncontradaException("Livro nao pode encontrado");
		}
		return casa;
	}
	public CasaShow salvar(CasaShow casa) {
		casa.setId(null);
		return casa = casaRep.save(casa);
	}
	public void deletar(Long id) {
		try {
			casaRep.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CasaNaoEncontradaException("O livro nao pode ser encontrado");
		}
	}
	
	public void atualizar(CasaShow casa) {
		verificareistencia(casa);
		casaRep.save(casa);
	}
	private void verificareistencia(CasaShow casa) {
		buscar(casa.getId());
	}
}












