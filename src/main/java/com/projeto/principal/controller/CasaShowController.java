package com.projeto.principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.projeto.principal.model.CasaShow;
import com.projeto.principal.repository.CasasShowsRep;


@Controller
@RequestMapping("/casadeshow")
public class CasaShowController {
	

	
	@Autowired	
	private CasasShowsRep casas;

	@RequestMapping
	public ModelAndView casa() {
		ModelAndView mv=new ModelAndView("CasaShows");
		mv.addObject(new CasaShow());
		List<CasaShow>todasCasas = casas.findAll();
		mv.addObject("casas",todasCasas);
		return mv;
	}

			
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated CasaShow casa,RedirectAttributes attributes ,Errors errors) {		
		ModelAndView mv= new ModelAndView("CasaShows");
		if(errors.hasErrors()) {			
			List<CasaShow>todasCasas = casas.findAll();
			mv.addObject("casas",todasCasas);
			return "redirect:/casadeshow";
		}		
		casas.save(casa);
				
		mv.addObject("mensagem", " Casa de Show salva com sucesso");		
		List<CasaShow>todasCasas = casas.findAll();
		mv.addObject("casas",todasCasas);		
		return "redirect:/casadeshow";
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable ("id") CasaShow casa) {				
		ModelAndView mv = new ModelAndView("CasaShows");
		mv.addObject(casa);
		List<CasaShow>todasCasas = casas.findAll();
		mv.addObject("casas",todasCasas);	
		return mv;
	}
	
	@RequestMapping(value="{id}" , method =RequestMethod.POST)
	public String excluir(@PathVariable Long id,RedirectAttributes attributes) {
		casas.deleteById(id);		
		attributes.addFlashAttribute("mensagem", "Titulo excluído com sucesso");
		return "redirect:/casadeshow";		
	}
}





















