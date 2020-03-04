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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projeto.principal.api.services.CasaShowServices;
import com.projeto.principal.model.CasaShow;




@RestController
@RequestMapping("/api/casa")
public class CasaShowResource {

	@Autowired
	private CasaShowServices casaservices;
	
	@GetMapping
	public ResponseEntity<List<CasaShow>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(casaservices.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody CasaShow casa) {
		casa = casaservices.salvar(casa);
		
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(casa.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity <?> buscar(@PathVariable ("id") Long id) {
		Optional<CasaShow> casa = casaservices.buscar(id);			
		
		CacheControl cacheControl = CacheControl.maxAge(20,TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(casa);		
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> remover(@PathVariable("id")Long id) {
		casaservices.deletar(id);
		return ResponseEntity.noContent().build();		
	}
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody CasaShow casa,
			@PathVariable ("id") Long id) {
		casa.setId(id);
		casaservices.atualizar(casa);
		return ResponseEntity.noContent().build();
	}	
}








