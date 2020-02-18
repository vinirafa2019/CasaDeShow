package com.projeto.principal.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.projeto.principal.model.Usuarios;
import com.projeto.principal.repository.UserRepository;

@Controller
@RequestMapping("/cadastro")
public class CadastroUsuario {

	@Autowired
	private UserRepository users;
	
	@RequestMapping
	public ModelAndView casa() {
		ModelAndView mv=new ModelAndView("Cadastro");
		mv.addObject(new Usuarios());
		List<Usuarios>todosUsuario = users.findAll();
		mv.addObject("usuario",todosUsuario);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastro(Usuarios usuario,RedirectAttributes attributes, Errors errors){
		ModelAndView mv = new ModelAndView("Cadastro");
		if(errors.hasErrors()) {			
			List<Usuarios>todosUsuario = users.findAll();
			mv.addObject("usuario",todosUsuario);
			return mv;
		}
		users.save(usuario);
		mv.addObject("mensagem","Usuario cadastrado com sucesso");
		mv.addObject(new Usuarios());
		List<Usuarios>todosUsuario = users.findAll();
		mv.addObject("usuario",todosUsuario);
		return mv;
	}	
}
