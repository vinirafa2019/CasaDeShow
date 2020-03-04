package com.projeto.principal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.principal.model.Evento;
import com.projeto.principal.model.ItensCompra;
import com.projeto.principal.model.Usuarios;
import com.projeto.principal.repository.EventosRep;
import com.projeto.principal.repository.UserRepository;

@Controller

public class Carrinho {

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();

	private Usuarios usuario;

	@Autowired
	private EventosRep eventos;

	@Autowired
	private UserRepository usuarios;



	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("Carrinhos");
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

		ModelAndView mv = new ModelAndView("Carrinhos");

		Optional<Evento> todosEventos = eventos.findById(id);
		Evento events = todosEventos.get();

		int des = 1;
		if (todosEventos.get().getQtddisponivel() > 0) {
			todosEventos.get().setQtddisponivel(todosEventos.get().getQtddisponivel() - des);
			mv.addObject(todosEventos.get().getQtddisponivel());
			eventos.save(todosEventos.get());

			int manter = 0;
			for (ItensCompra it : itensCompra) {
				if (it.getEven().getId().equals(events.getId())) {
					it.setQuantidade(it.getQuantidade() + 1);
					it.setValorunitario(it.getValorunitario() + it.getValorunitario());
					manter = 1;
				}
			}
			if (manter == 0) {
				ItensCompra item = new ItensCompra();
				item.setEven(events);
				item.setValorunitario(events.getValoringresso());
				item.setQuantidade(item.getQuantidade() + 1);
				item.setValortotal(item.getQuantidade() * item.getValorunitario());
				itensCompra.add(item);

			}
			mv.addObject("compraeventos", itensCompra);
		} else {
			mv.addObject("mensagem", "Ingresso esgotados!!");
		}
		return mv;
	}

	@RequestMapping("/{id}")
	public ModelAndView remover(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("Carrinhos");
		for (ItensCompra it : itensCompra) {
			if (it.getEven().getId().equals(id)) {
				itensCompra.remove(it);
				break;
			}
		}
		mv.addObject("compraeventos", itensCompra);
		return mv;
	}

	public void buscarusuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String username = autenticado.getName();
			usuario = usuarios.buscarUsername(username).get(0);
		}
	}



	@RequestMapping("/historico/{id}")
	public ModelAndView historico(ItensCompra compre) {
		buscarusuarioLogado();
		ModelAndView mv = new ModelAndView("Historico");
		List<ItensCompra> todasCompras = itensCompra;
		mv.addObject("compraevento", todasCompras);
		mv.addObject("usuario", usuario);
		return mv;
	}
}
