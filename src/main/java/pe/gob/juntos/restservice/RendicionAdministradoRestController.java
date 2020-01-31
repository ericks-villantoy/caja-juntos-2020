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

import pe.gob.juntos.entity.Clase;
import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.entity.RutaSede;
import pe.gob.juntos.request.DetalleRendicionRequest;
import pe.gob.juntos.request.DetalleRendicionTarifarioRequest;
import pe.gob.juntos.request.RendicionAdministradoRequest;
import pe.gob.juntos.request.RendicionConsultaAdministradoRequest;
import pe.gob.juntos.request.RendicionDetalleConsultaAdministradoRequest;
import pe.gob.juntos.response.RendicionAdministradoResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.response.RutaDetalleRendicionResponse;
import pe.gob.juntos.service.RendicionAdministradoService;

@RestController
public class RendicionAdministradoRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(RendicionAdministradoRestController.class);
	
	@Autowired
	private RendicionAdministradoService rendicionAdministradoService;
	
	@CrossOrigin
	@RequestMapping(value="obtenerRendicionesAdministrado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RendicionAdministradoResponse> obtenerRendicionesAdministrado(@RequestBody RendicionConsultaAdministradoRequest req){	
		logger.info("obtenerListaPersonalUlees");
		List<RendicionAdministradoResponse> listadoRendiciones = new ArrayList<RendicionAdministradoResponse>();
		try {
			listadoRendiciones = rendicionAdministradoService.listarRendicionesAdministrado(req);
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerRendicionesAdministrado: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoRendiciones;
	}
	
	@CrossOrigin
	@RequestMapping(value="obtenerDetalleTarifarioRendicion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RutaDetalleRendicionResponse> obtenerDetalleTarifarioRendicion(@RequestBody DetalleRendicionTarifarioRequest req){	
		logger.info("obtenerListaPersonalUlees");
		List<RutaDetalleRendicionResponse> listadoDetalleTarifarioRendiciones = new ArrayList<RutaDetalleRendicionResponse>();
		try {
			listadoDetalleTarifarioRendiciones = rendicionAdministradoService.listarDetalleTarifarioRendicion(req);
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerDetalleTarifarioRendicion: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoDetalleTarifarioRendiciones;
	}
	
	@CrossOrigin
	@RequestMapping(value="obtenerDetalleRendicionesAdministrado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RendicionAdministradoResponse> obtenerDetalleRendicionesAdministrado(@RequestBody RendicionDetalleConsultaAdministradoRequest req){	
		logger.info("obtenerListaPersonalUlees");
		RendicionAdministradoResponse respuesta= new RendicionAdministradoResponse();
		try {
			respuesta = rendicionAdministradoService.obtenerRendicion(req.getIdRendicion());
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerDetalleRendicionesAdministrado: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RendicionAdministradoResponse>(respuesta, HttpStatus.OK);
		}
		return new ResponseEntity<RendicionAdministradoResponse>(respuesta, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="listarEspecificaGasto", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Especifica> listarEspecificaGasto(){	
		logger.info("listarEspecificaGasto");
		List<Especifica> listadoEspecifica = new ArrayList<Especifica>();
		try {
			listadoEspecifica = rendicionAdministradoService.listarEspecificaGasto();
		} catch (Exception e) {
			logger.error("Errorx Completo listarEspecificaGasto: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoEspecifica;
	}
	
	@CrossOrigin
	@RequestMapping(value="listarTiposClases", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Clase> listarTiposClases(){	
		logger.info("listarTiposClases");
		List<Clase> listadoClases = new ArrayList<Clase>();
		try {
			listadoClases = rendicionAdministradoService.listarTipoClase();
		} catch (Exception e) {
			logger.error("Errorx Completo listarTiposClases: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoClases;
	}
	
	@CrossOrigin
	@RequestMapping(value="listarTarifario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RutaSede> listarTarifario(){	
		logger.info("listarTarifario");
		List<RutaSede> listadoClases = new ArrayList<RutaSede>();
		try {
			listadoClases = rendicionAdministradoService.listarRutaSede();
		} catch (Exception e) {
			logger.error("Errorx Completo listarTarifario: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoClases;
	}
	
	@CrossOrigin
	@RequestMapping(value = "grabarRendicionAdministrado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> grabarRendicionAdministrado(@RequestBody RendicionAdministradoRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta = rendicionAdministradoService.registrarRendicionAdministrado(req);
			
		} catch (Exception e) {
			logger.error("Errorx Completo grabarRendicionAdministrado: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
		}

		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "grabarDetalleRendicionAdministrado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> grabarDetalleRendicionAdministrado(@RequestBody DetalleRendicionRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta = rendicionAdministradoService.registrarDetalleRendicionAdministrado(req);
			
		} catch (Exception e) {
			logger.error("Errorx Completo grabarDetalleRendicionAdministrado: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
		}

		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "grabarDetalleRendicionTarifarioAdministrado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> grabarDetalleRendicionTarifarioAdministrado(@RequestBody DetalleRendicionTarifarioRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta = rendicionAdministradoService.registrarDetalleRendicionTarifarioAdministrado(req);
			
		} catch (Exception e) {
			logger.error("Errorx Completo grabarDetalleRendicionTarifarioAdministrado: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
		}

		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK);
	}

	

}
