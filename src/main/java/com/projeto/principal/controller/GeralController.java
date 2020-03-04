package com.projeto.principal.controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.principal.model.CasaShow;
import com.projeto.principal.model.Evento;
import com.projeto.principal.repository.CasasShowsRep;
import com.projeto.principal.repository.EventosRep;



@Controller
@RequestMapping("/")
public class GeralController {
	
	private static String caminhoImagens= "C:/Users/VEDM/Desktop/imagens/";
	
	@Autowired
	private EventosRep eventos;
	@Autowired
	private CasasShowsRep casas;
		
	@RequestMapping(method = RequestMethod.GET, path = "/login")
	public String login() { 

		return "Login";			
	}

	
	@RequestMapping
	public ModelAndView compras() {
		ModelAndView mv=new ModelAndView("Home");
		List<Evento>todosEventos = eventos.findAll();
		mv.addObject("eventos",todosEventos);		
		List<CasaShow>todasCasas = casas.findAll();
		mv.addObject("casas",todasCasas);
		return mv;	
	}	
	
	@RequestMapping("/home/imagem/{imagem}")
	@ResponseBody
	public byte [] retornaimagem(@Validated @PathVariable("imagem") String imagem,RedirectAttributes attributes) throws IOException {
		File imagemArquivo = new File(caminhoImagens+imagem);
		if(imagem!=null || imagem.trim().length()>0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}	

	

	
}
