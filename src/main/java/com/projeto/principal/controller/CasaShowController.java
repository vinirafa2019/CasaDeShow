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

	@RequestMapping("/casadeshow")
	public String casa() {
		return "CasaShows";
	}

			
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated CasaShow casa, Errors errors,RedirectAttributes attributes) {
		casas.save(casa);
		attributes.addFlashAttribute("mensagem", " Casa de Show salva com sucesso");		
		return "redirect:/casadeshow";
	} 
	@RequestMapping
	public ModelAndView pesquisa() {	
		List<CasaShow>todasCasas = casas.findAll();
		ModelAndView mv=new ModelAndView("CasaShows");
		mv.addObject("casas",todasCasas);
		mv.addObject(new CasaShow());
		return mv;
	}
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") CasaShow casa) {		
		ModelAndView mv = new ModelAndView("CasaShows");
		mv.addObject(casa);
		return mv;
	}
	
	@RequestMapping(value="{id}" , method =RequestMethod.POST)
	public String excluir(@PathVariable Long id,RedirectAttributes attributes) {
		casas.deleteById(id);
		
		attributes.addFlashAttribute("mensagem", "Titulo excluído com sucesso");
		return "redirect:/casadeshow";
		
	}
}
//@RequestMapping(value="/casas")
//public ModelAndView view() {
//	List<CasaShow> todasCasas = cshow.findAll();
//	ModelAndView mv = new ModelAndView("CadastroCasa");
//	mv.addObject("cshow", todasCasas);
//	mv.addObject(new CasaShow());
//	mv.addObject("mensagem", "Casa Cadastrada com sucesso");
//	return mv;
//}
//
//
//@RequestMapping(value="/casas", method = RequestMethod.POST)
//public ModelAndView salvar(CasaShow casashow) {
//	this.view();
//	
//	cshow.save(casashow);
//	//Salvar no banco de dados
//	
//	return view();
//	
//}





















