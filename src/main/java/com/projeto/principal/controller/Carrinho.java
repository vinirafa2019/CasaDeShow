package com.projeto.principal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.MysqlType;
import com.projeto.principal.model.Compra;
import com.projeto.principal.model.Evento;
import com.projeto.principal.repository.ComprarRep;
import com.projeto.principal.repository.EventosRep;

@Controller

public class Carrinho {

	private List<Compra> itensCompra = new ArrayList<Compra>();

	@Autowired
	private EventosRep eventos;
	@Autowired 
	private ComprarRep compras;

	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("Carrinho");
		mv.addObject("compraeventos", itensCompra);
		return mv;
	}
	@RequestMapping("/historico")
	public ModelAndView historico() {
		ModelAndView mv = new ModelAndView("Historico");
		mv.addObject("compraeventos", itensCompra);
		return mv;
	}

	@RequestMapping("/adicionarCarrinho/{id}")
	public ModelAndView comprar(@PathVariable Long id, RedirectAttributes attributess) {

		ModelAndView mv = new ModelAndView("Carrinho");

		Optional<Evento> todosEventos = eventos.findById(id);
		Evento events = todosEventos.get();

		int manter = 0;
		for (Compra it : itensCompra) {
			if (it.getEven().getId().equals(events.getId())) {
				it.setQuantidade(it.getQuantidade() + 1);
				it.setValorunitario(it.getValorunitario()+it.getValorunitario());
				manter = 1;
				break;
			}

		}
		if (manter == 0) {
			Compra item = new Compra();
			item.setEven(events);
			item.setValorunitario(events.getValoringresso());
			item.setQuantidade(item.getQuantidade() + 1);
			item.setValortotal(item.getQuantidade()*item.getValorunitario());
			itensCompra.add(item);

		}
		mv.addObject("compraeventos", itensCompra);

		int des = 1;
		if (todosEventos.get().getQtddisponivel() > 0) {
			todosEventos.get().setQtddisponivel(todosEventos.get().getQtddisponivel() - des);
			mv.addObject(todosEventos.get().getQtddisponivel());
			eventos.save(todosEventos.get());
		} else {
			attributess.addFlashAttribute("mensagem", "Ingresso esgotados!!");
		}
		
		return mv;
	}
		
	@RequestMapping("/{id}")
	public String remover(@PathVariable Long id, RedirectAttributes attributes) {
		for(Compra it : itensCompra) {
			if(it.getEven().getId().equals(id)) {
				itensCompra.remove(it);
				break;
			}
		}
		return "redirect:/carrinho";
	}
	@RequestMapping("/historico/{id}")
	public ModelAndView finalizar(Compra compre) {
		ModelAndView mv= new ModelAndView("Historico");
		compras.save(compre);
		
		for(Compra it: itensCompra) {
			compras.save(compre);
		}
		
		
		mv.addObject("mensagem","Compra Efetuada com sucesso");
		List<Compra>todasCompras = compras.findAll(); 
		mv.addObject("compraevento",todasCompras);
		return mv;		
	}
}














