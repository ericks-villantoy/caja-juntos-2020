package pe.gob.juntos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/bandeja")
	public String Inicio(Model model) {
		
		// Envio de datos obligatorios para la vista
		model.addAttribute("view", "views/moduloBandeja/index");
		model.addAttribute("menuActivo", "");
		model.addAttribute("menuPrincipal", "");

		return "index";

	}
	
}
