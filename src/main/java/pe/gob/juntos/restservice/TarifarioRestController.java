package pe.gob.juntos.restservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.juntos.request.TarifarioConsultaRequest;
import pe.gob.juntos.request.TarifarioRequest;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.response.TarifarioResponse;
import pe.gob.juntos.service.TarifarioService;
import pe.gob.juntos.service.TarifarioServiceImpl;

@RestController
public class TarifarioRestController {

	private static final Logger logger = LoggerFactory.getLogger(TarifarioServiceImpl.class);
	
	@Autowired
	private TarifarioService tarifarioService;
	
	@CrossOrigin
	@RequestMapping(value="obtenerTarifarioRutas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TarifarioResponse> obtenerTarifarioRutas(@RequestBody TarifarioConsultaRequest req){	
		logger.info("obtenerListaPersonalUlees");
		List<TarifarioResponse> listadoAlcaldes = new ArrayList<TarifarioResponse>();
		try {
			listadoAlcaldes = tarifarioService.listarTarifarioRutas(req);
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerListaPersonalUlees: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoAlcaldes;
	}
	
	@CrossOrigin
	@RequestMapping(value = "grabarRutaSede", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> grabarRutaSede(@RequestBody TarifarioRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta = tarifarioService.registrarTarifario(req);
			
		} catch (Exception e) {
			logger.error("Errorx Completo grabarAlcalde: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
		}

		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
	}
	
}
