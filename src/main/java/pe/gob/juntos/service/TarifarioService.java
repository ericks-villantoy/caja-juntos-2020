package pe.gob.juntos.service;

import java.util.List;

import pe.gob.juntos.request.TarifarioConsultaRequest;
import pe.gob.juntos.request.TarifarioRequest;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.response.TarifarioResponse;

public interface TarifarioService {

	List<TarifarioResponse> listarTarifarioRutas(TarifarioConsultaRequest req);
	
	RespuestaGeneralResponse registrarTarifario(TarifarioRequest req);
}
