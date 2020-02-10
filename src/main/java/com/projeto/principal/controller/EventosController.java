package com.projeto.principal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.principal.model.Evento;
import com.projeto.principal.repository.EventosRep;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	private static final String EVENTO_VIEW ="Evento";
	@Autowired
	private EventosRep evento;
	
	
	@RequestMapping("/eventos")
	public String evento() {
		ModelAndView mv= new ModelAndView(EVENTO_VIEW);
		mv.addObject(new Evento());
		return "Evento";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Evento eventos ,Errors errors,RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return EVENTO_VIEW;
		}		
		evento.save(eventos);
		attributes.addFlashAttribute("mensagem", "Evento salvo com sucesso");
		return "redirect:/eventos";	
	}	
	
	public ModelAndView pesquisa() {	
		List<Evento>todosEventos = evento.findAll();
		ModelAndView mv=new ModelAndView("Evento");
		mv.addObject("evento",todosEventos);
		return mv;
	}
	
}





