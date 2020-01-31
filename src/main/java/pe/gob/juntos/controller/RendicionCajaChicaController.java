package pe.gob.juntos.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.gob.juntos.entity.ConfiguracionCajaChica;
import pe.gob.juntos.entity.ControlCierreMesCajaChica;
import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.service.ComunService;
import pe.gob.juntos.service.RendicionCajaChicaService;
import pe.gob.juntos.util.FechaUtil;
import pe.gob.juntos.util.SeguridadUtil;

@Controller
public class RendicionCajaChicaController {

	@Autowired
	private ComunService comunService;
	
	@Autowired
	private RendicionCajaChicaService rendicionCajaChicaService;
	
	@PreAuthorize("hasAnyAuthority('ROLE_002','ROLE_003')")
	@RequestMapping("/rendicionCajaChica")
	public String administrado(Model model) {
		String codigoSede = SeguridadUtil.obtenerRegion();
		
		try {
			
			model.addAttribute("puedeEnviar", false);
			
			if(SeguridadUtil.obtenerRegion().equals("00")) {
				List<Sede> listadoSedes = comunService.listarSedes();
				
				model.addAttribute("solo", false);
				model.addAttribute("codigoSede", codigoSede);
				model.addAttribute("listadoSedes", listadoSedes);
				
			}
			
			if(!SeguridadUtil.obtenerRegion().equals("00")) {
				Sede sede = comunService.obtenerSede(codigoSede);
				model.addAttribute("sede", sede);
				model.addAttribute("codigoSede", codigoSede);
				model.addAttribute("solo", true);
			}
			
			ConfiguracionCajaChica configuracion = rendicionCajaChicaService.obtenerConfiguracionSede(codigoSede);
			
			ControlCierreMesCajaChica control = rendicionCajaChicaService.obtenerControlCierreCajaChica(codigoSede);
			
			int anio = Integer.parseInt(FechaUtil.obtenerAnioActual());
			int mes = FechaUtil.obtenerMesActualEntero();
			
			if(configuracion.getAnio().intValue()== anio && configuracion.getMes().intValue()==mes) {
				model.addAttribute("puedeEnviar", true);
			}
			
			if(control.getEstado().equals("1")) {
				if(control.getMes().intValue()!=mes) {
					if(control.getAnio().intValue()!=anio) {
						model.addAttribute("puedeEnviar", true);
					}
				}else {
					if(control.getAnio().intValue()==anio) {
						model.addAttribute("puedeEnviar", false);
					}
				}
			}
			
			
		} catch (Exception e) {
			
		}
		
		model.addAttribute("listadoAnios", this.listarAnios());
		model.addAttribute("listadoMeses", this.listarMeses());
		
		// Envio de datos obligatorios para la vista
		model.addAttribute("view", "views/moduloRendicion/rendicionCajaChica/index");
		model.addAttribute("menuActivo", "menuRendicionCajaChica");
		model.addAttribute("menuPrincipal", "menuRendicion");

		return "index";

	}
	
	private List<String> listarAnios() {

		List<String> listadoAnios = new ArrayList<String>();
		LocalDate fechaActual = LocalDate.now();
		String anio = String.valueOf(fechaActual.getYear());
		String anio1 = String.valueOf(fechaActual.getYear() -1);
		
		listadoAnios.add(anio);
		listadoAnios.add(anio1);
		return listadoAnios;

	}
	
	private List<String> listarMeses() {

		List<String> listadoMeses = new ArrayList<String>();
		LocalDate fechaActual = LocalDate.now();
		int mes = fechaActual.getMonth().getValue();
		String mesString = String.valueOf(mes);
		String mesString1 = String.valueOf(mes - 1);
		if(mes==1)
			mesString1="12";
		
		listadoMeses.add(mesString);
		listadoMeses.add(mesString1);
		return listadoMeses;

	}

	
}
