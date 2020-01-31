package pe.gob.juntos.service;

import java.util.List;

import pe.gob.juntos.request.DetalleRendicionTarifarioRequest;
import pe.gob.juntos.request.RendicionAprobacionAdministradoRequest;
import pe.gob.juntos.request.RendicionConsultaAdministradoRequest;
import pe.gob.juntos.request.RendicionPagoItemAdministradoRequest;
import pe.gob.juntos.response.RendicionAdministradoResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.response.RutaDetalleRendicionResponse;

public interface RendicionAprobacionService {

	List<RendicionAdministradoResponse> listarRendicionesRegion(RendicionConsultaAdministradoRequest req);
	
	List<RutaDetalleRendicionResponse> listarDetalleTarifarioRendicion(DetalleRendicionTarifarioRequest req);
	
	RendicionAdministradoResponse obtenerRendicion(Long idRendicion);
	
	RespuestaGeneralResponse registrarRendicionAprobacion(RendicionAprobacionAdministradoRequest req);
	
	RespuestaGeneralResponse registrarPagoItem(RendicionPagoItemAdministradoRequest req);
}
