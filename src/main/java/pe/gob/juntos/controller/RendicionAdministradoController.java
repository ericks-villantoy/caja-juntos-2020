package pe.gob.juntos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.service.ComunService;
import pe.gob.juntos.util.SeguridadUtil;

@Controller
public class RendicionAdministradoController {

	@Autowired
	private ComunService comunService;
	
	@PreAuthorize("hasAuthority('ROLE_001')")
	@RequestMapping("/rendicionAdministrado")
	public String administrado(Model model) {
		String codigoSede = SeguridadUtil.obtenerRegion();
		try {
			Sede sede = comunService.obtenerSede(codigoSede);
			model.addAttribute("sede", sede);
		} catch (Exception e) {
			
		}
		
		// Envio de datos obligatorios para la vista
		model.addAttribute("view", "views/moduloRendicion/rendicionAdministrado/index");
		model.addAttribute("menuActivo", "menuRendicionAdministrado");
		model.addAttribute("menuPrincipal", "menuRendicion");

		return "index";

	}
	
	@RequestMapping("/rendicionAdministradoDetalle")
	public String administradoDetalle(Model model) {
		// Envio de datos obligatorios para la vista
		model.addAttribute("view", "views/moduloRendicion/rendicionAdministrado/rendicionDetalle");
		model.addAttribute("menuActivo", "menuRendicionAdministrado");
		model.addAttribute("menuPrincipal", "menuRendicion");

		return "index";

	}
	
	@RequestMapping("/rendicionAdministradoDetalle/{id}")
	public String administradoDetalleEditar(@PathVariable("id") Long idRendicion, Model model) {
		model.addAttribute("idRendicion", idRendicion);
		
		// Envio de datos obligatorios para la vista
		model.addAttribute("view", "views/moduloRendicion/rendicionAdministrado/rendicionDetalle");
		model.addAttribute("menuActivo", "");
		model.addAttribute("menuPrincipal", "");

		return "index";

	}
	
}
