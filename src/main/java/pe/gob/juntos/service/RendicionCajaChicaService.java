package pe.gob.juntos.service;

import java.util.List;

import pe.gob.juntos.entity.ConfiguracionCajaChica;
import pe.gob.juntos.entity.ControlCierreMesCajaChica;
import pe.gob.juntos.request.ReaperturaCierreCacjaChicaRequest;
import pe.gob.juntos.request.RendicionCajaChicaRequest;
import pe.gob.juntos.request.RendicionConsultaAdministradoRequest;
import pe.gob.juntos.response.ControlCierreMesCajaChicaResponse;
import pe.gob.juntos.response.DetalleRendicionResponse;
import pe.gob.juntos.response.RendicionCajaChicaResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;

public interface RendicionCajaChicaService {

	List<DetalleRendicionResponse> listarRendicionesRegionAprobadas(RendicionConsultaAdministradoRequest req);
	
	List<RendicionCajaChicaResponse> listarRendicionesCajaChica();
	
	RespuestaGeneralResponse enviarRendicionCajaChica(RendicionCajaChicaRequest req);
	
	ConfiguracionCajaChica obtenerConfiguracionSede(String idSede);
	
	ControlCierreMesCajaChica obtenerControlCierreCajaChica(String idSede);
	
	RespuestaGeneralResponse reaperturarCerrarCajaChica(ReaperturaCierreCacjaChicaRequest req);
	
	List<ControlCierreMesCajaChicaResponse> listarHistorialCierreReapertura(ReaperturaCierreCacjaChicaRequest req);
}
