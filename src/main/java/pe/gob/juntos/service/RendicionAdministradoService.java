package pe.gob.juntos.service;

import java.util.List;

import pe.gob.juntos.entity.Clase;
import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.entity.RutaSede;
import pe.gob.juntos.request.DetalleRendicionRequest;
import pe.gob.juntos.request.DetalleRendicionTarifarioRequest;
import pe.gob.juntos.request.RendicionAdministradoRequest;
import pe.gob.juntos.request.RendicionConsultaAdministradoRequest;
import pe.gob.juntos.response.RendicionAdministradoResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.response.RutaDetalleRendicionResponse;

public interface RendicionAdministradoService {

	List<RendicionAdministradoResponse> listarRendicionesAdministrado(RendicionConsultaAdministradoRequest req);
	
	List<RutaDetalleRendicionResponse> listarDetalleTarifarioRendicion(DetalleRendicionTarifarioRequest req);
	
	List<Especifica> listarEspecificaGasto();
	
	List<Clase> listarTipoClase();
	
	List<RutaSede> listarRutaSede();
	
	RespuestaGeneralResponse registrarRendicionAdministrado(RendicionAdministradoRequest req);
	
	RespuestaGeneralResponse registrarDetalleRendicionAdministrado(DetalleRendicionRequest req);
	
	RespuestaGeneralResponse registrarDetalleRendicionTarifarioAdministrado(DetalleRendicionTarifarioRequest req);
	
	RendicionAdministradoResponse obtenerRendicion(Long idRendicion);
	
}
