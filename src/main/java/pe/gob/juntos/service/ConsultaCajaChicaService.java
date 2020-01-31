package pe.gob.juntos.service;

import java.util.List;

import pe.gob.juntos.request.ConsultaRendicionCajaChicaRequest;
import pe.gob.juntos.response.DetalleRendicionCajaChicaResponse;

public interface ConsultaCajaChicaService {

	List<DetalleRendicionCajaChicaResponse> listarRendicionCajaChica(ConsultaRendicionCajaChicaRequest req) ;
	
}
