package com.projeto.principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.principal.model.Usuarios;
import com.projeto.principal.repository.UserRepository;

@Controller
@RequestMapping("/cadastro")
public class CadastroUsuario {

	@Autowired
	private UserRepository users;
	
	@RequestMapping
	public ModelAndView casa() {
		ModelAndView mv= new ModelAndView("Cadastro");
		mv.addObject(new Usuarios());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastro(@Validated Usuarios usuario,Errors errors){
		ModelAndView mv= new ModelAndView("Login");
		ModelAndView mc= new ModelAndView("Cadastro");
		
		if(errors.hasErrors()) {
			return mc;
		}		
		users.save(usuario);
		return mv;
	}	
	
}
