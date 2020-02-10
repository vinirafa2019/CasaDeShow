package com.projeto.principal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class GeralController {

	@RequestMapping("/")
	public String home() {
		return "Home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "Login";
	}	
	
	@RequestMapping("/eventos")
	public String evento() {
		return "Evento";
	}
}
