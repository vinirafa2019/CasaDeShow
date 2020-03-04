package com.projeto.principal.api.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projeto.principal.api.services.EventoServices;
import com.projeto.principal.model.Evento;



@RestController
@RequestMapping("/api/evento")
public class EventoResources {

	
	@Autowired
	private EventoServices eventoservice;


	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity< List<Evento>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoservice.listar());
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Evento eventos) {
		eventos = eventoservice.salvar(eventos);
		
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(eventos.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity <?> buscar( @PathVariable ("id") Long id) {
		Optional<Evento> livro=eventoservice.buscar(id);			
		
		CacheControl cacheControl = CacheControl.maxAge(20,TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);		
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> remover(@PathVariable("id")Long id) {
		eventoservice.deletar(id);
		return ResponseEntity.noContent().build();		
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Evento eventos,
			@PathVariable ("id") Long id) {
		eventos.setId(id);
		eventoservice.atualizar(eventos);
		return ResponseEntity.noContent().build();
	}	
	
//	@RequestMapping(value ="/{id}/comentarios",method = RequestMethod.POST)
//	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroid,
//			@RequestBody Comentario comentario){	
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		
//		comentario.setUsuario(auth.getName());
//		
//		livrosServices.salvarcomentario(livroid, comentario);		
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
//		
//		return ResponseEntity.created(uri).build();	
//	}
//	
//	@ApiOperation("Lista os Comentarios")
//	@RequestMapping(value ="/{id}/comentarios",method = RequestMethod.GET)
//	public ResponseEntity<List<Comentario>> listaComentarios(@PathVariable("id")Long livroid){
//		List<Comentario>comentarios =livrosServices.listarcomentario(livroid);
//		
//		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
//	}
	
}
