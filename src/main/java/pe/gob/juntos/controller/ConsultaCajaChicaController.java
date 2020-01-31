package pe.gob.juntos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.response.RendicionCajaChicaResponse;
import pe.gob.juntos.service.ComunService;
import pe.gob.juntos.util.SeguridadUtil;

@Controller
public class ConsultaCajaChicaController {

	@Autowired
	private ComunService comunService;
	
	@PreAuthorize("hasAnyAuthority('ROLE_002','ROLE_003')")
	@RequestMapping("/consulraRendicionCajaChica")
	public String consultaRendicionCajaChica(Model model) {
		String codigoSede = SeguridadUtil.obtenerRegion();
		try {
			if(SeguridadUtil.obtenerRegion().equals("00")) {
				List<Sede> listadoSedes = comunService.listarSedes();
				
				model.addAttribute("solo", false);
				model.addAttribute("listadoSedes", listadoSedes);
				
			}
			
			if(!SeguridadUtil.obtenerRegion().equals("00")) {
				Sede sede = comunService.obtenerSede(codigoSede);
				model.addAttribute("sede", sede);	
				model.addAttribute("solo", true);
			}
			
		} catch (Exception e) {
			
		}
		
		List<RendicionCajaChicaResponse> listadoEnvios = comunService.listarRendicionesCajaChica();
		
		model.addAttribute("listadoEnvios", listadoEnvios);
		// Envio de datos obligatorios para la vista
		model.addAttribute("view", "views/moduloConsultas/index");
		model.addAttribute("menuActivo", "consultaCajaChica");
		model.addAttribute("menuPrincipal", "menuConsultas");

		return "index";

	}
	
}
