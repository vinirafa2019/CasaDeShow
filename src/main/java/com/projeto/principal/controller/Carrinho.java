package com.projeto.principal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.principal.model.Compra;
import com.projeto.principal.model.Evento;
import com.projeto.principal.repository.EventosRep;

@Controller
public class Carrinho {
	
	private List<Compra>itensCompra = new ArrayList<Compra>();
	
	@Autowired
	private EventosRep eventos;

	
	@RequestMapping("/carrinho")
	public ModelAndView carrinho(){
		ModelAndView mv = new ModelAndView("Carrinho");
		return mv;
		
	}

	@RequestMapping("/adicionarCarrinho/{id}")
	public ModelAndView comprar(@PathVariable Long id, RedirectAttributes attributess) {

			ModelAndView mv = new ModelAndView("Carrinho");
			
			Optional<Evento> todosEventos = eventos.findById(id);
			Evento events = todosEventos.get();
			Compra item = new Compra();
			item.setEven(events);
			item.setValorunitario(events.getValoringresso());
			item.setQuantidade(item.getQuantidade()+1);
			itensCompra.add(item);
			mv.addObject("compraeventos",itensCompra);
			
			int des = 1 ;
			if (todosEventos.get().getQtddisponivel() > 0) {
					todosEventos.get().setQtddisponivel(todosEventos.get().getQtddisponivel() - des);
			mv.addObject(todosEventos.get().getQtddisponivel());
			eventos.save(todosEventos.get());}
			else {
				attributess.addFlashAttribute("mensagem","Ingresso esgotados!!");
			}
			return mv;	
	}
}
