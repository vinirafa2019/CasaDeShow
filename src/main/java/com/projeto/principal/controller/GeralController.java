package com.projeto.principal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.principal.model.CasaShow;
import com.projeto.principal.model.Evento;
import com.projeto.principal.repository.CasasShowsRep;
import com.projeto.principal.repository.EventosRep;






@Controller
@RequestMapping("/")
public class GeralController {
	
	@Autowired
	private EventosRep eventos;
	@Autowired
	private CasasShowsRep casas;
	
	@RequestMapping
	public ModelAndView home() {
		ModelAndView mv=new ModelAndView("Home");
		List<Evento>todosEventos = eventos.findAll();
		mv.addObject("eventos",todosEventos);
		
		List<CasaShow>todasCasas = casas.findAll();
		mv.addObject("casas",todasCasas);
		
		return mv;	
	}
	
	
	
	
	@RequestMapping("/login")
	public String login() {
		return "Login";
	}	
}
