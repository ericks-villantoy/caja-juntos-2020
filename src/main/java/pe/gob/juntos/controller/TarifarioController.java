package pe.gob.juntos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.service.ComunService;
import pe.gob.juntos.util.SeguridadUtil;

@Controller
public class TarifarioController {

	@Autowired
	private ComunService comunService;
	
	@RequestMapping("/tarifario")
	public String Inicio(Model model) {
		List<Sede> listadoSedes = new ArrayList<Sede>();
		
		try {
			if(SeguridadUtil.hasRole("ROLE_003"))
			{
				listadoSedes = comunService.listarSedes();
			}
			else
			{				
				listadoSedes = comunService.listarSedesByCodigo(SeguridadUtil.obtenerRegion());
			}			
			
			model.addAttribute("listadoSedes", listadoSedes);
		} catch (Exception e) {
			
		}
		
		// Envio de datos obligatorios para la vista
		model.addAttribute("view", "views/moduloMantenimiento/tarifario/index");
		model.addAttribute("menuActivo", "");
		model.addAttribute("menuPrincipal", "");

		return "index";

	}
}
