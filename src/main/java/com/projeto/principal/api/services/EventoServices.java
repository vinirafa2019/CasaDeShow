package com.projeto.principal.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import com.projeto.principal.exceptions.EventoNaoEncontradoException;
import com.projeto.principal.model.Evento;
import com.projeto.principal.repository.EventosRep;




@Service
public class EventoServices {
	@Autowired
	private EventosRep eventorep;


	
	public List<Evento> listar() {
		return eventorep.findAll();
	}

	public Evento salvar(Evento eventos) {
		if(eventos.getId()!=null) {
			Optional<Evento> a = eventorep.findById(eventos.getId());			
			if(a.isPresent()) {
				throw new EventoExistenteException("O Autor Ja existe");
			}
		}
		return eventorep.save(eventos);
	}
	
	public Optional<Evento> buscar(Long id) {
		Optional<Evento> Evento = eventorep.findById(id);

		if (Evento.isEmpty()) {
			throw new EventoNaoEncontradoException("Evento nao pode encontrado");
		}
		return Evento;
	}

	public void deletar(Long id) {
		try {
			eventorep.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EventoNaoEncontradoException("O Evento nao pode ser encontrado");
		}
	}

	public void atualizar(Evento eventos) {
		verificareistencia(eventos);
		eventorep.save(eventos);
	}

	private void verificareistencia(Evento eventos) {
		buscar(eventos.getId());
	}
//	public Comentario salvarcomentario(Long Eventoid,Comentario comentario) {
//		Optional<Evento> Evento =buscar(Eventoid);
//		
//		comentario.setEventos(Evento.get());
//		comentario.setData(new Date());
//		return comentariosrepositorios.save(comentario);	
//	}
//	public List<Comentario>listarcomentario(Long Eventoid){
//		Optional<Evento> Evento =buscar(Eventoid);
//		
//		return Evento.get().getComentarios();
//		
//	}

}
