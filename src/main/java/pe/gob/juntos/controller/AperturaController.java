package pe.gob.juntos.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.gob.juntos.entity.Anio;
import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.service.ComunService;
import pe.gob.juntos.util.SeguridadUtil;


@Controller
public class AperturaController {

	@Autowired
	private ComunService comunService;
	
	@RequestMapping("/apertura")
	public String administrado(Model model) {

		List<Sede> listadoSedes = new ArrayList<Sede>();
		List<Anio> listadoAnio = new ArrayList<Anio>();
		List<Especifica> listadoEspecifica = new ArrayList<Especifica>();
		
		
		String codigoSede = SeguridadUtil.obtenerRegion();
		
		LocalDateTime ahora = LocalDateTime.now();
				
		try {								
			//System.out.println("ahora.getYear(): "+ ahora.getYear() );
			
			for (int i = ahora.getYear(); i > 2018 ; i--) {
				Anio anioBucle = new Anio();
				anioBucle.setAnio(i);				
				anioBucle.setDescripcion(String.valueOf(i));
				listadoAnio.add(anioBucle);
				
			}
			model.addAttribute("listadoAnio",listadoAnio);
			
			Anio anioActual = new Anio();
			anioActual.setAnio(ahora.getYear());				
			anioActual.setDescripcion(String.valueOf(ahora.getYear()));
			model.addAttribute("selAnio",anioActual);
			
			
			Sede sede = comunService.obtenerSede(codigoSede);
			model.addAttribute("sede", sede);
			
			
			if(SeguridadUtil.hasRole("ROLE_003"))
			{
				listadoSedes = comunService.listarSedes();
			}
			else
			{				
				listadoSedes = comunService.listarSedesByCodigo(SeguridadUtil.obtenerRegion());
			}
			
			model.addAttribute("listadoSedes", listadoSedes);
			
			
			listadoEspecifica = comunService.listarEspecificas();
			model.addAttribute("listadoEspecificas", listadoEspecifica);
			
			
		} catch (Exception e) {
			
		}
		
		// Envio de datos obligatorios para la vista
		model.addAttribute("view", "views/moduloAperturaCierre/apertura/index");
		model.addAttribute("menuActivo", "");
		model.addAttribute("menuPrincipal", "");

		return "index";

	}
	
}
