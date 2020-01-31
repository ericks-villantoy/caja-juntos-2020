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

import pe.gob.juntos.request.AperturaCierreRequest;
import pe.gob.juntos.response.AperturaCierreResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.service.AperturaCierreService;

@RestController
public class AperturaCierreRestController {

	private static final Logger logger = LoggerFactory.getLogger(AperturaCierreRestController.class);
	
	@Autowired
	private AperturaCierreService aperturaCierreService;
	
	@CrossOrigin
	@RequestMapping(value="obtenerAperturasCaja", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AperturaCierreResponse> obtenerAperturasCaja(@RequestBody AperturaCierreRequest req){	
		logger.info("obtenerAperturasCaja");
		List<AperturaCierreResponse> listadoAperturas = new ArrayList<AperturaCierreResponse>();
		try {
			listadoAperturas = aperturaCierreService.listarAperturaCierre(req);
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerAperturasCaja: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoAperturas;
	}
	
	@CrossOrigin
	@RequestMapping(value = "grabarAperturaCierre", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> grabarAperturaCierre(@RequestBody AperturaCierreRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta = aperturaCierreService.registrarApertura(req);
			
		} catch (Exception e) {
			logger.error("Errorx Completo grabarAlcalde: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
		}

		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
	}
	
}
