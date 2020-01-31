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

import pe.gob.juntos.request.RendicionAprobacionAdministradoRequest;
import pe.gob.juntos.request.RendicionConsultaAdministradoRequest;
import pe.gob.juntos.request.RendicionDetalleConsultaAdministradoRequest;
import pe.gob.juntos.request.RendicionPagoItemAdministradoRequest;
import pe.gob.juntos.response.RendicionAdministradoResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.service.RendicionAprobacionService;

@RestController
public class RendicionAprobacionRestController {

	private static final Logger logger = LoggerFactory.getLogger(RendicionAprobacionRestController.class);
	
	@Autowired
	private RendicionAprobacionService rendicionAprobacionService;
	
	@CrossOrigin
	@RequestMapping(value="obtenerRendicionesAprobaciones", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RendicionAdministradoResponse> obtenerRendicionesAprobaciones(@RequestBody RendicionConsultaAdministradoRequest req){	
		logger.info("obtenerRendicionesAprobaciones");
		List<RendicionAdministradoResponse> listadoRendiciones = new ArrayList<RendicionAdministradoResponse>();
		try {
			listadoRendiciones = rendicionAprobacionService.listarRendicionesRegion(req);
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerRendicionesAprobaciones: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoRendiciones;
	}
	
	@CrossOrigin
	@RequestMapping(value="obtenerDetalleRendicionesAprobacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RendicionAdministradoResponse> obtenerDetalleRendicionesAprobacion(@RequestBody RendicionDetalleConsultaAdministradoRequest req){	
		logger.info("obtenerListaPersonalUlees");
		RendicionAdministradoResponse respuesta= new RendicionAdministradoResponse();
		try {
			respuesta = rendicionAprobacionService.obtenerRendicion(req.getIdRendicion());
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerDetalleRendicionesAdministrado: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RendicionAdministradoResponse>(respuesta, HttpStatus.OK);
		}
		return new ResponseEntity<RendicionAdministradoResponse>(respuesta, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "aprobarRendicionAdministrado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> aprobarRendicionAdministrado(@RequestBody RendicionAprobacionAdministradoRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta = rendicionAprobacionService.registrarRendicionAprobacion(req);
			
		} catch (Exception e) {
			logger.error("Errorx Completo grabarRendicionAdministrado: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
		}

		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "marcarPagadoItem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> marcarPagadoItem(@RequestBody RendicionPagoItemAdministradoRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta = rendicionAprobacionService.registrarPagoItem(req);
			
		} catch (Exception e) {
			logger.error("Errorx Completo marcarPagadoItem: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
		}

		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
	}
	
}
