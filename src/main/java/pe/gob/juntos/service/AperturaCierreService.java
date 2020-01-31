package pe.gob.juntos.service;

import java.util.List;
import pe.gob.juntos.request.AperturaCierreRequest;
import pe.gob.juntos.request.TarifarioRequest;
import pe.gob.juntos.response.AperturaCierreResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;


public interface AperturaCierreService {
	
	List<AperturaCierreResponse> listarAperturaCierre(AperturaCierreRequest req);
	
	Double calcularEjecucion(Long idEspecifica, String CodRegion);
	
	RespuestaGeneralResponse registrarApertura(AperturaCierreRequest req);

}
