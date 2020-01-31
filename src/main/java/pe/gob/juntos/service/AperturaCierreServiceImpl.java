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
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;

import pe.gob.juntos.entity.AperturaCierre;
import pe.gob.juntos.entity.DetalleRendicion;
import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.entity.QAperturaCierre;
import pe.gob.juntos.entity.QEspecifica;
import pe.gob.juntos.entity.QSede;
import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.request.AperturaCierreRequest;
import pe.gob.juntos.response.AperturaCierreResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.respository.AperturaCierreRepository;
import pe.gob.juntos.respository.DetalleRendicionRepository;
import pe.gob.juntos.respository.EspecificaRepository;
import pe.gob.juntos.respository.SedeRepository;
import pe.gob.juntos.util.SeguridadUtil;

@Service("aperturaCierreService")
public class AperturaCierreServiceImpl implements AperturaCierreService {

	private static final Logger logger = LoggerFactory.getLogger(AperturaCierreServiceImpl.class);
	
	@Autowired
	EntityManager em;

	@Autowired
	private DetalleRendicionRepository detalleRendicionRepository;
	
	@Autowired
	private AperturaCierreRepository aperturaCierreRepository;
	
	@Autowired
	private SedeRepository sedeRepository;
	
	@Autowired
	private EspecificaRepository especificaRepository;
	
	
	@Override
	public List<AperturaCierreResponse> listarAperturaCierre(AperturaCierreRequest req) {
		
		List<AperturaCierre> listadoAperturaCierre = new ArrayList<AperturaCierre>();
		List<AperturaCierreResponse> listadoACR = new ArrayList<AperturaCierreResponse>();
		try {
			QAperturaCierre aperturaCierre = QAperturaCierre.aperturaCierre;
			//QRendicion rendicion = QRendicion.rendicion;
			QEspecifica especifica = QEspecifica.especifica;
			QSede sede = QSede.sede;
			
			JPAQuery<AperturaCierre> query = new JPAQuery<AperturaCierre>(em);

			//DateTimePath<Date> fechaInicioDesdeFiltro = rendicion.fechaInicio;
			StringExpression idSedeFiltro = sede.idSede;
			NumberPath<Long> anio = aperturaCierre.anio;
			
			Predicate mConsultaAnio = null;
			Predicate mConsultaSede = null;
			Predicate mConsultaRestrictiva = aperturaCierre.eliminado.eq("0").and(aperturaCierre.vigente.eq("1"));
						
			//mConsultaSede = idSedeFiltro.eq(SeguridadUtil.obtenerRegion());	
			mConsultaSede = idSedeFiltro.eq(req.getCodRegion());
			mConsultaAnio = anio.eq(req.getAnio());	
			/*
			if (	req.getFechaInicioTxt() != null && req.getFechaInicioTxt().length()>0 &&
					req.getFechaFinTxt() != null && req.getFechaFinTxt().length()>0
			   ){
				mConsultaSede = fechaInicioDesdeFiltro.between(req.getFechaInicio(), req.getFechaFin());	
				
			}*/
			
			listadoAperturaCierre = query.from(aperturaCierre)
								.join(aperturaCierre.especifica, especifica)
								.join(aperturaCierre.sede, sede)
								.distinct()
								.where(	mConsultaAnio, 
										mConsultaSede, 
										mConsultaRestrictiva
									  )

								.fetch();

			/*listadoAperturaCierre.forEach(obj -> {

				AperturaCierreResponse ape = new AperturaCierreResponse(); 
				BeanUtils.copyProperties(obj, ape);
				
				//ape.setAnio(obj.getAnio());
				
				Double totalEjecucion = calcularEjecucion(ape.getEspecifica().getIdEspecifica(),ape.getSede().getIdSede());
				Double saldo = ape.getMontoApertura() - ape.getMontoLiquidacion() - totalEjecucion;
				
				ape.setMontoEjecucion(totalEjecucion);
				ape.setSaldo(saldo);
				
				if(ape.getFlagCerrado().equals("1"))
					ape.setDescCerrado("Cerrado");
				else
					ape.setDescCerrado("Abierto");
				
				
				listadoACR.add(ape);
				
			});*/ //comentado solo para java 8 en adelante
			
			for(AperturaCierre a : listadoAperturaCierre) {
				
				AperturaCierreResponse ape = new AperturaCierreResponse(); 
				
				BeanUtils.copyProperties(a, ape);
				
				//ape.setAnio(obj.getAnio());
				
				Double totalEjecucion = calcularEjecucion(ape.getEspecifica().getIdEspecifica(),ape.getSede().getIdSede());
				Double saldo = ape.getMontoApertura() - ape.getMontoLiquidacion() - totalEjecucion;
				
				ape.setMontoEjecucion(totalEjecucion);
				ape.setSaldo(saldo);
				
				if(ape.getFlagCerrado().equals("1"))
					ape.setDescCerrado("Cerrado");
				else
					ape.setDescCerrado("Abierto");
				
				
				listadoACR.add(ape);	
			}
			
			
		} catch (Exception e) {
			logger.error("error completo listarAperturaCierre: " + ExceptionUtils.getFullStackTrace(e));
		}
		
		return listadoACR;
	}
	
	@Override
	public Double calcularEjecucion(Long idEspecifica, String codRegion) {
		
		List<DetalleRendicion> listDetalleRendicion = new ArrayList<DetalleRendicion>();
		Double totalEjecutado = 0.0;
				
		listDetalleRendicion = detalleRendicionRepository.obtenerDetalleRendicionByEspecificaYregion(idEspecifica, codRegion);
						
		for (DetalleRendicion detalleRendicion : listDetalleRendicion) {
			//System.out.println("detalleRendicion.getRendicion().getEstado(): "+detalleRendicion.getRendicion().getEstado());
			//System.out.println("detalleRendicion.getFechaPagado(): "+detalleRendicion.getFechaPagado());
			if(detalleRendicion.getRendicion().getEstado().equals("9") && detalleRendicion.getFechaPagado()!=null)
			{
				//System.out.println("sumando - detalleRendicion.getIdDetalleRendicion():"+detalleRendicion.getIdDetalleRendicion());
				totalEjecutado = totalEjecutado + detalleRendicion.getImporte();
			}
		}		
		
		return totalEjecutado;
	}
	
	@Override
	public RespuestaGeneralResponse registrarApertura(AperturaCierreRequest req) {
		
		AperturaCierre aperturaCierre = new AperturaCierre();
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta.setExito(true);
			/*if (req.isEliminar()) {
				mRespuesta = this.eliminarTarifarioSede(req);
				return mRespuesta;
			}*/

			if(req.getIdAperturaCierre()!=null)
			{
				aperturaCierre = aperturaCierreRepository.findOne(req.getIdAperturaCierre());
			
				if(req.getIdSede()!=null && req.getAnio()!=null && req.getIdEspecifica()!=null) {
				
					Sede sede = sedeRepository.findOne(req.getIdSede());
					Especifica especifica = especificaRepository.findOne(req.getIdEspecifica());
					
					BeanUtils.copyProperties(req, aperturaCierre);
									
					aperturaCierre.setSede(sede);
					aperturaCierre.setEspecifica(especifica);
					aperturaCierre.setEliminado("0");
					aperturaCierre.setFlagCerrado("0");
					aperturaCierre.setUsuarioModificacion(SeguridadUtil.obtenerCodigoUsuario());
					aperturaCierre.setFechaModificacion(new Date());
					AperturaCierre aperturaCierreSave = aperturaCierreRepository.save(aperturaCierre);
					
					if (aperturaCierreSave==null) {
						mRespuesta.setExito(false);
						mRespuesta.setRespuesta("Inconveniente registrando Apertura.");
						return mRespuesta;
		
					}
				}else {
					mRespuesta.setExito(false);
					mRespuesta.setRespuesta("Inconveniente recuperando Sede, Año, o Específica.");
					return mRespuesta;
				}
			}
			else
			{
				if(req.getIdSede()!=null && req.getAnio()!=null && req.getIdEspecifica()!=null) {
					
					Sede sede = sedeRepository.findOne(req.getIdSede());
					Especifica especifica = especificaRepository.findOne(req.getIdEspecifica());
					
					BeanUtils.copyProperties(req, aperturaCierre);
									
					aperturaCierre.setSede(sede);
					aperturaCierre.setEspecifica(especifica);
					aperturaCierre.setEliminado("0");
					aperturaCierre.setFlagCerrado("0");
					aperturaCierre.setUsuarioCreacion(SeguridadUtil.obtenerCodigoUsuario());
					aperturaCierre.setFechaCreacion(new Date());
					AperturaCierre aperturaCierreSave = aperturaCierreRepository.save(aperturaCierre);
					
					if (aperturaCierreSave==null) {
						mRespuesta.setExito(false);
						mRespuesta.setRespuesta("Inconveniente registrando Apertura.");
						return mRespuesta;
		
					}
				}else {
					mRespuesta.setExito(false);
					mRespuesta.setRespuesta("Inconveniente recuperando Sede, Año, o Específica.");
					return mRespuesta;
				}
				
			}
			
		} catch (Exception e) {
			logger.error("error completo registrarApertura: " + ExceptionUtils.getFullStackTrace(e));
			mRespuesta.setExito(false);
			mRespuesta.setRespuesta("Inconveniente registrando Apertura.");
			return mRespuesta;
		}
		return mRespuesta;
	}
	
}
