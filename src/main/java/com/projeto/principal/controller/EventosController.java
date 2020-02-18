package com.projeto.principal.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.projeto.principal.model.CasaShow;
import com.projeto.principal.model.Evento;
import com.projeto.principal.model.TodosEstilos;
import com.projeto.principal.repository.CasasShowsRep;
import com.projeto.principal.repository.EventosRep;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	private static final String EVENTO_VIEW ="Eventos";
	@Autowired
	private EventosRep eventos;
	@Autowired
	private CasasShowsRep casas;
	
	
	@RequestMapping
	public ModelAndView evento() {
		ModelAndView mv=new ModelAndView("Eventos");
		mv.addObject(new Evento());
		List<Evento>todosEventos = eventos.findAll();
		mv.addObject("eventos",todosEventos);
		List<CasaShow>todasCasas = casas.findAll();
		mv.addObject("casas",todasCasas);
		return mv;
	}

			

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Evento evento, Errors errors,RedirectAttributes attributes) {		
		ModelAndView mv= new ModelAndView("Eventos");
		if(errors.hasErrors()) {			
		
			List<Evento>todosEventos = eventos.findAll();
			mv.addObject("eventos",todosEventos);
			List<CasaShow>todasCasas = casas.findAll();
			mv.addObject("casas",todasCasas);
			return mv;
		}		
		evento.setQtddisponivel(evento.getCapacidade());
		eventos.save(evento);
				
		mv.addObject("mensagem", " Evento salvo com sucesso");		
		mv.addObject(new Evento());
		List<Evento>todosEventos = eventos.findAll(); 
		mv.addObject("eventos",todosEventos);
		List<CasaShow>todasCasas = casas.findAll();
		mv.addObject("casas",todasCasas);
		

		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable ("id") Evento evento) {				
		ModelAndView mv = new ModelAndView(EVENTO_VIEW);
		mv.addObject(evento);
		List<Evento>todosEventos = eventos.findAll();
		mv.addObject("eventos",todosEventos);
		List<CasaShow>todasCasas = casas.findAll();
		mv.addObject("casas",todasCasas);
		return mv;
	}		
	@RequestMapping(value="{id}" , method =RequestMethod.POST)
	public String excluir(@PathVariable Long id,RedirectAttributes attributes) {
		eventos.deleteById(id);
		
		attributes.addFlashAttribute("mensagem", "Evento excluído com sucesso");
		return "redirect:/eventos";		
	}
	@ModelAttribute("todosEstilos")
	public List<TodosEstilos>todosStatusTitulo(){
		return Arrays.asList(TodosEstilos.values());
	}	
}





