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
import pe.gob.juntos.request.VisualizaArchivoRequest;
import pe.gob.juntos.response.FlujoAprobacionResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.response.VisualizaArchivoResponse;
import pe.gob.juntos.service.ComunService;

@RestController
public class ComunRestController {

	private static final Logger logger = LoggerFactory.getLogger(RendicionAdministradoRestController.class);
	
	
	@Autowired
	private ComunService comunService;
	
	@RequestMapping(value = "/visualizaArchivoPdf",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VisualizaArchivoResponse> visualizaArchivoPdf(@RequestBody VisualizaArchivoRequest visualizaArchivoRequest) {
	    
		VisualizaArchivoResponse mRespuesta = new VisualizaArchivoResponse();
		try {
			mRespuesta = comunService.visualizaArchivoPdf(visualizaArchivoRequest);
		} catch (Exception e) {
			logger.error("Errorx Completo visualizaArchivoPdf: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<VisualizaArchivoResponse>(mRespuesta, HttpStatus.BAD_REQUEST) ;
		}
		
		return new ResponseEntity<VisualizaArchivoResponse>(mRespuesta, HttpStatus.OK) ;
	}
	
	@RequestMapping(value = "/eliminarArchivoPdf",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaGeneralResponse> eliminarArchivoPdf(@RequestBody VisualizaArchivoRequest visualizaArchivoRequest) {
	    
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		Long idRendicion = visualizaArchivoRequest.getIdRendicion();
		boolean eliminoArchivo=false;
		try {
	    	eliminoArchivo = comunService.eliminarArchivo(idRendicion);
			mRespuesta.setExito(eliminoArchivo);
	    } catch (Exception e) {
			logger.error("errorx completo eliminarArchivoPdf: " + ExceptionUtils.getFullStackTrace(e));
			return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK) ;
		}
		
		return new ResponseEntity<RespuestaGeneralResponse>(mRespuesta, HttpStatus.OK) ;
	}
	
	@CrossOrigin
	@RequestMapping(value="listarFlujoAprobacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FlujoAprobacionResponse> listarFlujoAprobacion(@RequestBody RendicionAprobacionAdministradoRequest req){
		
		logger.info("obtenerListaPersonalUlees");
		List<FlujoAprobacionResponse> listadoRendiciones = new ArrayList<FlujoAprobacionResponse>();
		try {
			listadoRendiciones = comunService.listarFlujoAprobacion(req);
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerRendicionesAdministrado: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoRendiciones;
		
	}
	
}
