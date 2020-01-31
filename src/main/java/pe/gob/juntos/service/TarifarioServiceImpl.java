package pe.gob.juntos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;

import pe.gob.juntos.entity.QRutaSede;
import pe.gob.juntos.entity.QSede;
import pe.gob.juntos.entity.RutaSede;
import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.request.TarifarioConsultaRequest;
import pe.gob.juntos.request.TarifarioRequest;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.response.TarifarioResponse;
import pe.gob.juntos.respository.RutaSedeRepository;
import pe.gob.juntos.respository.SedeRepository;
import pe.gob.juntos.util.SeguridadUtil;

@Service("tarifarioService")
public class TarifarioServiceImpl implements TarifarioService {

	private static final Logger logger = LoggerFactory.getLogger(TarifarioServiceImpl.class);
	
	@Autowired
	EntityManager em;
	
	@Autowired
	private RutaSedeRepository rutaSedeRepository;
	
	@Autowired
	private SedeRepository sedeRepository;
	
	@Override
	public List<TarifarioResponse> listarTarifarioRutas(TarifarioConsultaRequest req) {
		
		List<RutaSede> listadoTarifarioRutas = new ArrayList<RutaSede>();
		List<TarifarioResponse> listadoTarifarioResponse = new ArrayList<TarifarioResponse>();
		
		try {
			
			QRutaSede rutasede = QRutaSede.rutaSede;
			QSede sede = QSede.sede;
			
			JPAQuery<RutaSede> query = new JPAQuery<RutaSede>(em);
			
			listadoTarifarioRutas = (List<RutaSede>) rutaSedeRepository.findAll();
			
			StringExpression idSedeFiltro = sede.idSede;
			StringExpression nombreRutaFiltro = sede.descripcionSede;
			
			Predicate mConsultaSede = null;
			Predicate mConsultaSedeRuta = null;
			Predicate mConsultaRestrictiva = rutasede.eliminado.eq("0").and(rutasede.vigente.eq("1"));
			
			if(req.getIdSede()!=null && req.getIdSede().length()>0) {
				mConsultaSede = idSedeFiltro.eq(req.getIdSede());
			}
			
			if(req.getNombreRuta()!=null && req.getNombreRuta().length()>0) {
				mConsultaSedeRuta = nombreRutaFiltro.eq(req.getNombreRuta());
			}
			
			listadoTarifarioRutas = query.from(rutasede)
					.distinct()
					.where(	mConsultaSede, 
							mConsultaSedeRuta, 
							mConsultaRestrictiva
						  )

					.fetch();	
			
			
			/*listadoTarifarioRutas.forEach(t->{
				TarifarioResponse resp = new TarifarioResponse();
				BeanUtils.copyProperties(t, resp);
				listadoTarifarioResponse.add(resp);
			});*/
			
			for(RutaSede rs : listadoTarifarioRutas) {
				TarifarioResponse resp = new TarifarioResponse();
				BeanUtils.copyProperties(rs, resp);
				listadoTarifarioResponse.add(resp);
			}		
			
		} catch (Exception e) {
			logger.error("error completo listarTarifarioRutas: " +ExceptionUtils.getFullStackTrace(e));
		}
		
		return listadoTarifarioResponse;
	}

	@Override
	public RespuestaGeneralResponse registrarTarifario(TarifarioRequest req) {
		
		RutaSede rutaSede = new RutaSede();
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta.setExito(true);
			if (req.isEliminar()) {
				mRespuesta = this.eliminarTarifarioSede(req);
				return mRespuesta;
			}

			if(req.getIdRutaSede()!=null)
				rutaSede = rutaSedeRepository.findOne(req.getIdRutaSede());
			
			if(req.getIdSede()!=null) {
			
				Sede sede = sedeRepository.findOne(req.getIdSede());
				
				BeanUtils.copyProperties(req, rutaSede);
				rutaSede.setUbigeoPartida("X");
				rutaSede.setUbigeoLlegada("Y");
				
				rutaSede.setSede(sede);
				rutaSede.setEliminado("0");
				rutaSede.setUsuarioCreacion("deseguridad");
				rutaSede.setFechaCreacion(new Date());
				RutaSede rutaSedeSave = rutaSedeRepository.save(rutaSede);
				
				if (rutaSedeSave==null) {
					mRespuesta.setExito(false);
					mRespuesta.setRespuesta("Inconveniente registrando Ruta de Sede");
					return mRespuesta;
	
				}
			}else {
				mRespuesta.setExito(false);
				mRespuesta.setRespuesta("Inconveniente recuperando Sede");
				return mRespuesta;
			}
			
		} catch (Exception e) {
			logger.error("error completo registrarTarifario: " + ExceptionUtils.getFullStackTrace(e));
			mRespuesta.setExito(false);
			mRespuesta.setRespuesta("Inconveniente registrando Ruta de Sede");
			return mRespuesta;
		}
		return mRespuesta;
	}
	
	private RespuestaGeneralResponse eliminarTarifarioSede(TarifarioRequest req) {
		RespuestaGeneralResponse response = new RespuestaGeneralResponse();
		
		try {
			response.setExito(true);
			if (req.getIdRutaSede() == null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente registrando identificador de la sede a dar de baja");
				return response;
			}
			RutaSede rutaSede = rutaSedeRepository.findOne(req.getIdRutaSede());
			
			if(rutaSede==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente recuperando ruta de la sede a dar de baja");
				return response;
			}
			
			rutaSede.setEliminado("1");
			rutaSede.setUsuarioModificacion(SeguridadUtil.obtenerUsuario());
			rutaSede.setFechaModificacion(new Date());
			RutaSede rutaSedeSave = rutaSedeRepository.save(rutaSede);
			
			if(rutaSedeSave==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente eliminado registro");
				return response;
			}
			
		} catch (Exception e) {
			logger.error("error completo eliminarTarifarioSede: " + ExceptionUtils.getFullStackTrace(e));
			response.setExito(false);
			response.setRespuesta("Inconveniente eliminado registro");
			return response;
		}
		
		return response;
	}

}
