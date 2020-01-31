package pe.gob.juntos.service;

import java.util.List;

import pe.gob.juntos.entity.Administrado;
import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.request.RendicionAprobacionAdministradoRequest;
import pe.gob.juntos.request.VisualizaArchivoRequest;
import pe.gob.juntos.response.FlujoAprobacionResponse;
import pe.gob.juntos.response.RendicionCajaChicaResponse;
import pe.gob.juntos.response.VisualizaArchivoResponse;

public interface ComunService {

	List<Sede> listarSedes();
	
	List<Sede> listarSedesByCodigo(String codigo);
	
	Sede obtenerSede(String codigoRegion);
	
	Administrado obtenerAdministrado(String idAdministrado);
	
	VisualizaArchivoResponse visualizaArchivoPdf(VisualizaArchivoRequest visualizaArchivoRequest);
	
	boolean eliminarArchivo(Long idArchivo);
	
	List<FlujoAprobacionResponse> listarFlujoAprobacion(RendicionAprobacionAdministradoRequest req);
	
	List<RendicionCajaChicaResponse> listarRendicionesCajaChica();
	
	List<Especifica> listarEspecificas();
}
