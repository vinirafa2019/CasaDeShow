package com.projeto.principal.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView compras() {
		ModelAndView mv=new ModelAndView("Home");
		List<Evento>todosEventos = eventos.findAll();
		mv.addObject("eventos",todosEventos);		
		List<CasaShow>todasCasas = casas.findAll();
		mv.addObject("casas",todasCasas);
		return mv;	
	}
	
	@RequestMapping("{id}")
	public String comprar(@PathVariable Long id, RedirectAttributes attributess) {
			System.out.println(id);	
			ModelAndView mv = new ModelAndView("Home");
			
			Optional<Evento> todosEventos = eventos.findById(id);
			int des = 1 ;
			if (todosEventos.get().getQtddisponivel() > 0) {
					todosEventos.get().setQtddisponivel(todosEventos.get().getQtddisponivel() - des);
			mv.addObject(todosEventos.get().getQtddisponivel());
			eventos.save(todosEventos.get());}
			else {
				attributess.addFlashAttribute("mensagem","Ingresso esgotados!!");
			}
			System.out.println(todosEventos.get().getQtddisponivel());
			return "redirect:/";	
	}
	
	

	@RequestMapping("/login")
	public String login() {
		return "Login";
	}	
}
