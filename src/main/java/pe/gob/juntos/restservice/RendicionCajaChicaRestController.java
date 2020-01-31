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

import pe.gob.juntos.request.ReaperturaCierreCacjaChicaRequest;
import pe.gob.juntos.request.RendicionCajaChicaRequest;
import pe.gob.juntos.request.RendicionConsultaAdministradoRequest;
import pe.gob.juntos.response.ControlCierreMesCajaChicaResponse;
import pe.gob.juntos.response.DetalleRendicionResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.service.RendicionCajaChicaService;

@RestController
public class RendicionCajaChicaRestController {

	private static final Logger logger = LoggerFactory.getLogger(RendicionCajaChicaRestController.class);
	
	@Autowired
	private RendicionCajaChicaService rendicionCajaChicaService;
	
	@CrossOrigin
	@RequestMapping(value="obtenerDetalleRendicionesAprobadas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DetalleRendicionResponse> obtenerRendicionesAprobaciones(@RequestBody RendicionConsultaAdministradoRequest req){	
		logger.info("obtenerRendicionesAprobaciones");
		List<DetalleRendicionResponse> listadoDetalleRendiciones = new ArrayList<DetalleRendicionResponse>();
		try {
			listadoDetalleRendiciones = rendicionCajaChicaService.listarRendicionesRegionAprobadas(req);
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerRendicionesAprobaciones: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoDetalleRendiciones;
	}
	
	@CrossOrigin
	@RequestMapping(value = "enviarRendicionCajaChica", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> enviarRendicionCajaChica(@RequestBody RendicionCajaChicaRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta = rendicionCajaChicaService.enviarRendicionCajaChica(req);
			
		} catch (Exception e) {
			logger.error("Errorx Completo marcarPagadoItem: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
		}

		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "reaperturarCerrarCajaChica", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> reaperturarCerrarCajaChica(@RequestBody ReaperturaCierreCacjaChicaRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta = rendicionCajaChicaService.reaperturarCerrarCajaChica(req);
			
		} catch (Exception e) {
			logger.error("Errorx Completo reaperturarCerrarCajaChica: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
		}

		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(value="obtenerHistorialApeturaCierre", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ControlCierreMesCajaChicaResponse> obtenerHistorialApeturaCierre(@RequestBody ReaperturaCierreCacjaChicaRequest req){	
		logger.info("obtenerRendicionesAprobaciones");
		List<ControlCierreMesCajaChicaResponse> listadoHistorial = new ArrayList<ControlCierreMesCajaChicaResponse>();
		try {
			listadoHistorial = rendicionCajaChicaService.listarHistorialCierreReapertura(req);
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerRendicionesAprobaciones: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoHistorial;
	}
	
}
