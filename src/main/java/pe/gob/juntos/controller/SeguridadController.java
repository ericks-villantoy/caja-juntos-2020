package pe.gob.juntos.controller;


/**
*
* 
* 
* @author Jose Cuya
* 
*/

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.gob.juntos.util.SeguridadUtil;



@Controller
public class SeguridadController {

	private static final Logger logger = LoggerFactory.getLogger(SeguridadController.class); 
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		
		
		
		logger.info("Iniciando Seguridad");
		model.addAttribute("view", "views/moduloSeguridad/index");
		
		
		if (!SeguridadUtil.isAnonymousAuthenticationToken()){
			return "forward:/bandeja";
		} 
					
	return "login";
    }
	
	
	
	
	@RequestMapping("/accesoDenegado")
    public String accesoDenegado(Model model) {
	 // Envio de datos obligatorios para la vista 
		model.addAttribute("view", "views/moduloSeguridad/accesoDenegado");
        return "login";
    }
	
	@RequestMapping("/noEncontrado")
    public String noEncontrado(Model model) {
		 // Envio de datos obligatorios para la vista 
		model.addAttribute("view", "views/moduloSeguridad/error");
        return "login";
    }
	
	


}
