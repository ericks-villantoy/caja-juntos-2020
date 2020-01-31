package pe.gob.juntos.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.io.BaseEncoding;
import com.google.common.io.Files;

import pe.gob.juntos.entity.Administrado;
import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.entity.FlujoAprobacion;
import pe.gob.juntos.entity.Rendicion;
import pe.gob.juntos.entity.RendicionCajaChica;
import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.entity.VistaAccesoSeguridad;
import pe.gob.juntos.request.RendicionAprobacionAdministradoRequest;
import pe.gob.juntos.request.VisualizaArchivoRequest;
import pe.gob.juntos.response.FlujoAprobacionResponse;
import pe.gob.juntos.response.RendicionCajaChicaResponse;
import pe.gob.juntos.response.VisualizaArchivoResponse;
import pe.gob.juntos.respository.AdministradoRepository;
import pe.gob.juntos.respository.EspecificaRepository;
import pe.gob.juntos.respository.FlujoAprobacionRepository;
import pe.gob.juntos.respository.RendicionCajaChicaRepository;
import pe.gob.juntos.respository.RendicionRepository;
import pe.gob.juntos.respository.SedeRepository;
import pe.gob.juntos.respository.VistaAccesoSeguridadRepository;
import pe.gob.juntos.util.FechaUtil;
import pe.gob.juntos.util.SeguridadUtil;

@Service("comunService")
public class ComunServiceImpl implements ComunService {

	private static final Logger logger = LoggerFactory.getLogger(TarifarioServiceImpl.class);

	@Autowired
	EntityManager em;

	@Autowired
	private SedeRepository sedeRepository;

	@Autowired
	private AdministradoRepository administradoRepository;

	@Autowired
	private RendicionRepository rendicionRepository;

	@Autowired
	private RendicionCajaChicaRepository rendicionCajaChicaRepository;

	@Autowired
	private VistaAccesoSeguridadRepository vistaAccesoSeguridadRepository;

	@Autowired
	private FlujoAprobacionRepository flujoAprobacionRepository;

	@Autowired
	private EspecificaRepository especificaRepository;

	@Value("${sistema.download.path}")
	private String carpetaTemporalPDF;

	@Override
	public List<Sede> listarSedes() {
		List<Sede> listadoSedes = new ArrayList<Sede>();
		try {
			listadoSedes = (List<Sede>) sedeRepository.findAll();
		} catch (Exception e) {
			logger.error("Error Completo listarSedes: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoSedes;
	}

	@Override
	public Administrado obtenerAdministrado(String idAdministrado) {
		Administrado administrado = administradoRepository.findOne(idAdministrado);
		return administrado;
	}

	@Override
	public VisualizaArchivoResponse visualizaArchivoPdf(VisualizaArchivoRequest req) {
		VisualizaArchivoResponse mRespuesta = new VisualizaArchivoResponse();

		byte[] file = null;
		String mPdfBase64= null;

		try {

			String nombreArchivo = req.getNombreArchivo();
			File miPDF = new File(carpetaTemporalPDF+ nombreArchivo);

			if(!miPDF.exists() && !miPDF.isDirectory()) {
				mRespuesta.setArchivoCreado(false);
				return mRespuesta;
			}

			file = Files.toByteArray(miPDF);

			mPdfBase64 = new String(BaseEncoding.base64().encode(file));
			mRespuesta.setArchivoBase64(mPdfBase64);
			mRespuesta.setArchivoCreado(true);
			//debe ser guardado en ftp o tener un ambiente que soporte muchos archivos

		} catch (IOException e) {
			logger.error("Errorx Completo visualizaArchivoPdf: " + ExceptionUtils.getFullStackTrace(e));
			mRespuesta.setArchivoCreado(false);
			return mRespuesta;
		}

		return mRespuesta;
	}

	@Override
	public Sede obtenerSede(String codigoRegion) {
		Sede sede = sedeRepository.findOne(codigoRegion);
		return sede;
	}

	@Override
	public boolean eliminarArchivo(Long idRendicion) {

		Rendicion rendicionsave=null;

		try {
			Rendicion rendicion = rendicionRepository.findOne(idRendicion);

			rendicion.setDocumentoReferencia("");
			rendicion.setUsuarioModificacion(SeguridadUtil.obtenerCodigoUsuario());
			rendicion.setFechaModificacion(new Date());
			rendicionsave = rendicionRepository.save(rendicion);

			if(rendicionsave==null)
				return false;

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public List<FlujoAprobacionResponse> listarFlujoAprobacion(RendicionAprobacionAdministradoRequest req) {

		List<FlujoAprobacionResponse> listadoFlujoAprobacion = new ArrayList<FlujoAprobacionResponse>();

		List<FlujoAprobacion> listadoFlujo = flujoAprobacionRepository.buscarFlujosdeAprobacionRendicion(req.getIdRendicion());

		/*listadoFlujo.forEach(f->{
			FlujoAprobacionResponse resp = new FlujoAprobacionResponse();

			switch (f.getEstadoFin().trim()) {
			case "3":
				resp.setEstadoAprobacion("Observado");
				resp.setPerfil("Jefe");
				break;

			case "4":
				resp.setEstadoAprobacion("Aprobado Sub Jefe");
				resp.setPerfil("Sub Jefe");
				break;

			case "5":
				resp.setEstadoAprobacion("Aprobado Jefe");
				resp.setPerfil("Jefe");
				break;

			case "9":
				resp.setEstadoAprobacion("Aprobado Tesorería");
				resp.setPerfil("Tesoreria");
				break;

			}
			String usuario = f.getUsuarioAprobacion();
			VistaAccesoSeguridad vUsuario= vistaAccesoSeguridadRepository.findOne(usuario);
			resp.setUsuarioAprobacion(vUsuario.getUsuario().toUpperCase());
			resp.setFechaAprobacion("["+FechaUtil.ConvertirFechaDDMMYYYY(f.getFechaAprobacion())+"]");

			listadoFlujoAprobacion.add(resp);
		});*/ //solo para java 8 en adelante

		for(FlujoAprobacion f: listadoFlujo) {

			FlujoAprobacionResponse resp = new FlujoAprobacionResponse();

			switch (f.getEstadoFin().trim()) {
			case "3":
				resp.setEstadoAprobacion("Observado");
				resp.setPerfil("Jefe");
				break;

			case "4":
				resp.setEstadoAprobacion("Aprobado Sub Jefe");
				resp.setPerfil("Sub Jefe");
				break;

			case "5":
				resp.setEstadoAprobacion("Aprobado Jefe");
				resp.setPerfil("Jefe");
				break;

			case "9":
				resp.setEstadoAprobacion("Aprobado Tesorería");
				resp.setPerfil("Tesoreria");
				break;

			}
			
			String usuario = f.getUsuarioAprobacion();
			VistaAccesoSeguridad vUsuario= vistaAccesoSeguridadRepository.findOne(usuario);
			resp.setUsuarioAprobacion(vUsuario.getUsuario().toUpperCase());
			resp.setFechaAprobacion("["+FechaUtil.ConvertirFechaDDMMYYYY(f.getFechaAprobacion())+"]");

			listadoFlujoAprobacion.add(resp);

		}

		return listadoFlujoAprobacion;
	}

	@Override
	public List<RendicionCajaChicaResponse> listarRendicionesCajaChica() {
		List<RendicionCajaChicaResponse> listadoRendicionesCajaChicaResponse = new ArrayList<RendicionCajaChicaResponse>();
		List<RendicionCajaChica> listadoRendicionesCajaChica = (List<RendicionCajaChica>) rendicionCajaChicaRepository.findAll();

		try {			
			/*listadoRendicionesCajaChica.forEach(cc->{
				RendicionCajaChicaResponse resp = new RendicionCajaChicaResponse();
				BeanUtils.copyProperties(cc, resp);
				listadoRendicionesCajaChicaResponse.add(resp);
			});*/ //solo para java 8 en adelante
			
			for(RendicionCajaChica r: listadoRendicionesCajaChica) {
				RendicionCajaChicaResponse resp = new RendicionCajaChicaResponse();
				BeanUtils.copyProperties(r, resp);
				listadoRendicionesCajaChicaResponse.add(resp);	
			}			

		} catch (Exception e) {
			logger.error("error completo listarRendicionesCajaChica: " + ExceptionUtils.getFullStackTrace(e));
		}

		return listadoRendicionesCajaChicaResponse;
	}	

	@Override
	public List<Sede> listarSedesByCodigo(String codigo) {
		List<Sede> listadoSedes = new ArrayList<Sede>();
		try {
			listadoSedes.add(sedeRepository.findOne(codigo));			
		} catch (Exception e) {
			logger.error("Error Completo listarSedes: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoSedes;
	}

	@Override
	public List<Especifica> listarEspecificas() {
		List<Especifica> listarEspecificas = new ArrayList<Especifica>();
		try {
			listarEspecificas = (List<Especifica>) especificaRepository.findAll();
		} catch (Exception e) {
			logger.error("Error Completo listarEspecificas: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listarEspecificas;
	}


}
