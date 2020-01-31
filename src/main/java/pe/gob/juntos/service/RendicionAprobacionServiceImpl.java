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
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;

import pe.gob.juntos.entity.Administrado;
import pe.gob.juntos.entity.DetalleRendicion;
import pe.gob.juntos.entity.QAdministrado;
import pe.gob.juntos.entity.QRendicion;
import pe.gob.juntos.entity.QSede;
import pe.gob.juntos.entity.Rendicion;
import pe.gob.juntos.entity.RutaDetalleRendicion;
import pe.gob.juntos.entity.VistaDatosComisionado;
import pe.gob.juntos.request.DetalleRendicionTarifarioRequest;
import pe.gob.juntos.request.RendicionAprobacionAdministradoRequest;
import pe.gob.juntos.request.RendicionConsultaAdministradoRequest;
import pe.gob.juntos.request.RendicionPagoItemAdministradoRequest;
import pe.gob.juntos.response.DetalleRendicionResponse;
import pe.gob.juntos.response.EstadoAprobacionResponse;
import pe.gob.juntos.response.RendicionAdministradoResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.response.RutaDetalleRendicionResponse;
import pe.gob.juntos.respository.DetalleRendicionRepository;
import pe.gob.juntos.respository.ProcedimientosRepository;
import pe.gob.juntos.respository.RendicionRepository;
import pe.gob.juntos.respository.RutaRendicionDetalleRepository;
import pe.gob.juntos.respository.VistaDatosComisionadoRepository;
import pe.gob.juntos.util.SeguridadUtil;

@Service("rendicionAprobacionService")
public class RendicionAprobacionServiceImpl implements RendicionAprobacionService {

	private static final Logger logger = LoggerFactory.getLogger(RendicionAprobacionServiceImpl.class);

	@Autowired
	EntityManager em;

	@Autowired
	private RutaRendicionDetalleRepository rutaRendicionDetalleRepository;

	@Autowired
	private RendicionRepository rendicionRepository;

	@Autowired
	private DetalleRendicionRepository detalleRendicionRepository;

	@Autowired
	private ProcedimientosRepository procedimientosRepository;

	@Autowired
	private VistaDatosComisionadoRepository vistaDatosComisionadoRepository;

	@Override
	public List<RendicionAdministradoResponse> listarRendicionesRegion(RendicionConsultaAdministradoRequest req) {
		List<Rendicion> listadoRendicion = new ArrayList<Rendicion>();
		List<RendicionAdministradoResponse> listadoRendicionesAdministrado = new ArrayList<RendicionAdministradoResponse>();
		String codigoRegion = SeguridadUtil.obtenerRegion();
		String usuario = SeguridadUtil.obtenerUsuario();

		try {
			QRendicion rendicion = QRendicion.rendicion;
			QAdministrado administrado = QAdministrado.administrado;
			QSede sede = QSede.sede;

			JPAQuery<Rendicion> query = new JPAQuery<Rendicion>(em);

			DateTimePath<Date> fechaInicioDesdeFiltro = rendicion.fechaInicio;
			StringExpression idSedeFiltro = sede.idSede;
			NumberExpression<Long> idUnidadFiltro = rendicion.sedeUnidad;


			Predicate mConsultaFechaInicio = null;
			Predicate mConsultaSede = null;
			Predicate mConsultaUnidad = null;
			Predicate mConsultaRestrictiva = rendicion.eliminado.eq("0").and(rendicion.vigente.eq("1"));

			Predicate mConsultaEstado = rendicion.estado.in("2","3","4","5");


			mConsultaSede = idSedeFiltro.eq(codigoRegion);

			if(codigoRegion.equals("00")) {
				if (!SeguridadUtil.hasRole("ROLE_003")) {
					VistaDatosComisionado vistaComisionado= vistaDatosComisionadoRepository.obtenerComisionadoUsuario(usuario);
					mConsultaUnidad = idUnidadFiltro.eq(vistaComisionado.getCodigoUnidad());	
				}
			}

			if (	req.getFechaInicioTxt() != null && req.getFechaInicioTxt().length()>0 &&
					req.getFechaFinTxt() != null && req.getFechaFinTxt().length()>0
					){
				mConsultaFechaInicio = fechaInicioDesdeFiltro.between(req.getFechaInicio(), req.getFechaFin());	

			}

			listadoRendicion = query.from(rendicion)
					.join(rendicion.administrado, administrado)
					.join(administrado.sede, sede)
					.distinct()
					.where(	mConsultaFechaInicio, 
							mConsultaSede, 
							mConsultaRestrictiva,
							mConsultaEstado,
							mConsultaUnidad
							)

					.fetch();

			/*listadoRendicion.forEach(obj -> {

				RendicionAdministradoResponse ren = new RendicionAdministradoResponse();
				BeanUtils.copyProperties(obj, ren);
				listadoRendicionesAdministrado.add(ren);

			});*/

			for(Rendicion r : listadoRendicion) {				
				RendicionAdministradoResponse ren = new RendicionAdministradoResponse();
				BeanUtils.copyProperties(r, ren);
				listadoRendicionesAdministrado.add(ren);				
			}		


		} catch (Exception e) {
			logger.error("error completo listarRendicionesRegion: " + ExceptionUtils.getFullStackTrace(e));
		}

		return listadoRendicionesAdministrado;
	}

	@Override
	public List<RutaDetalleRendicionResponse> listarDetalleTarifarioRendicion(DetalleRendicionTarifarioRequest req) {
		List<RutaDetalleRendicionResponse> listadoResponse = new ArrayList<RutaDetalleRendicionResponse>();

		List<RutaDetalleRendicion> listadoDetalleTarifarioRendicion = rutaRendicionDetalleRepository
				.obtenerRutaRendicionDetallePorDetalle(req.getIdDetalleRendicion());

		/*listadoDetalleTarifarioRendicion.forEach(r -> {
			RutaDetalleRendicionResponse response = new RutaDetalleRendicionResponse();
			BeanUtils.copyProperties(r, response);
			listadoResponse.add(response);
		});*/

		for(RutaDetalleRendicion rd : listadoDetalleTarifarioRendicion) {				
			RutaDetalleRendicionResponse response = new RutaDetalleRendicionResponse();
			BeanUtils.copyProperties(rd, response);
			listadoResponse.add(response);				
		}		

		return listadoResponse;
	}

	@Override
	public RendicionAdministradoResponse obtenerRendicion(Long idRendicion) {
		RendicionAdministradoResponse rendicionResponse = new RendicionAdministradoResponse();
		List<DetalleRendicionResponse> listadoDetallesRendicion = new ArrayList<DetalleRendicionResponse>();

		Rendicion rendicion = new Rendicion(); 
		Administrado administrado=null;

		try {

			if(idRendicion!=null) {

				//if(rendicionRepository.existsById(idRendicion)) { //cambio de version del spring boot

				if(rendicionRepository.exists(idRendicion)) {

					rendicion = rendicionRepository.findOne(idRendicion);

					BeanUtils.copyProperties(rendicion, rendicionResponse);

					/*rendicion.getListadoDetallesRendicion().forEach(d->{
						DetalleRendicionResponse detRes = new DetalleRendicionResponse();
						BeanUtils.copyProperties(d, detRes);
						List<RutaDetalleRendicionResponse> listadoRutaDetallesRendicion = new ArrayList<RutaDetalleRendicionResponse>();
						d.getListadoRutaDetalleRendicion().forEach(r->{
							RutaDetalleRendicionResponse rutaDetalleR = new RutaDetalleRendicionResponse();
							BeanUtils.copyProperties(r, rutaDetalleR);
							listadoRutaDetallesRendicion.add(rutaDetalleR);
						});
						detRes.setListadoRutaDetalleRendicion(listadoRutaDetallesRendicion);
						listadoDetallesRendicion.add(detRes);
					});*/

					for(DetalleRendicion d : rendicion.getListadoDetallesRendicion()) {

						DetalleRendicionResponse detRes = new DetalleRendicionResponse();

						BeanUtils.copyProperties(d, detRes);

						List<RutaDetalleRendicionResponse> listadoRutaDetallesRendicion = new ArrayList<RutaDetalleRendicionResponse>();

						for(RutaDetalleRendicion dr : d.getListadoRutaDetalleRendicion()) {
							RutaDetalleRendicionResponse rutaDetalleR = new RutaDetalleRendicionResponse();
							BeanUtils.copyProperties(dr, rutaDetalleR);
							listadoRutaDetallesRendicion.add(rutaDetalleR);
						}					

						detRes.setListadoRutaDetalleRendicion(listadoRutaDetallesRendicion);

						listadoDetallesRendicion.add(detRes);
					}
					administrado = rendicion.getAdministrado();					

				}
			}
			
			boolean puedeAprobar = verificarPuedeAprobar(rendicion.getEstado());
			rendicionResponse.setPuedeAprobar(puedeAprobar);
			rendicionResponse.setListadoDetallesRendicion(listadoDetallesRendicion);
			rendicionResponse.setAdministrado(administrado);

		} catch (Exception e) {
			logger.error("error completo obtenerRendicion: " + ExceptionUtils.getFullStackTrace(e));
		}

		return rendicionResponse;
	}
	

	@Override
	public RespuestaGeneralResponse registrarRendicionAprobacion(RendicionAprobacionAdministradoRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		try {
			mRespuesta.setExito(true);

			String comentario="";
			String estadoNuevo="";

			EstadoAprobacionResponse estadoAprobacion = this.obtenerEstadoAprobacion(req);
			estadoNuevo = estadoAprobacion.getEstado();
			comentario = estadoAprobacion.getObservacion();

			Long idRendicion= req.getIdRendicion();

			mRespuesta = procedimientosRepository.flujoAprobacion(idRendicion, estadoNuevo, comentario);
			mRespuesta.setIdRendicion(req.getIdRendicion());

		} catch (Exception e) {
			logger.error("error completo registrarTarifario: " + ExceptionUtils.getFullStackTrace(e));
			mRespuesta.setExito(false);
			mRespuesta.setRespuesta("Inconveniente registrando Ruta de Sede");
			return mRespuesta;
		}
		return mRespuesta;
	}

	@Override
	public RespuestaGeneralResponse registrarPagoItem(RendicionPagoItemAdministradoRequest req) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();

		try {
			mRespuesta.setExito(true);
			DetalleRendicion detalleRendicion= detalleRendicionRepository.findOne(req.getIdDetalleRendicion());

			if(detalleRendicion==null) {
				mRespuesta.setExito(false);
				mRespuesta.setRespuesta("Inconveniente recuperando detalle rendicion");
				return mRespuesta;
			}

			detalleRendicion.setUsuarioRegistraPagado(SeguridadUtil.obtenerCodigoUsuario());
			detalleRendicion.setFechaPagado(new Date());
			detalleRendicion.setUsuarioModificacion(SeguridadUtil.obtenerCodigoUsuario());
			detalleRendicion.setFechaModificacion(new Date());

			DetalleRendicion detalleRendicionSave= detalleRendicionRepository.save(detalleRendicion);

			if(detalleRendicionSave==null) {
				mRespuesta.setExito(false);
				mRespuesta.setRespuesta("Inconveniente regsitrando pagado detalle");
				return mRespuesta;
			}

		} catch (Exception e) {
			mRespuesta.setExito(false);
			mRespuesta.setRespuesta("Inconveniente recuperando detalle rendicion");
			return mRespuesta;
		}

		return mRespuesta;
	}

	private EstadoAprobacionResponse obtenerEstadoAprobacion(RendicionAprobacionAdministradoRequest req) {
		String estadoNuevo="3";
		String comentario="";
		int accion = req.getAccion();
		EstadoAprobacionResponse resp = new EstadoAprobacionResponse();

		if(SeguridadUtil.obtenerRegion().equals("00")) {
			if (SeguridadUtil.hasRole("ROLE_004"))
				estadoNuevo="4";
			if (SeguridadUtil.hasRole("ROLE_005"))
				estadoNuevo="5";
			if (SeguridadUtil.hasRole("ROLE_003"))
				estadoNuevo="9";

		}

		if(!SeguridadUtil.obtenerRegion().equals("00")) {
			if (SeguridadUtil.hasRole("ROLE_005"))
				estadoNuevo="5";
			if (SeguridadUtil.hasRole("ROLE_002"))
				estadoNuevo="9";

		}

		if(accion==2) {
			comentario = req.getObservacion();
			estadoNuevo="3";
		}

		resp.setEstado(estadoNuevo);
		resp.setObservacion(comentario);

		return resp;
	}

	private boolean verificarPuedeAprobar (String estadoActual) {
		boolean puedeAprobar= false;
		if(SeguridadUtil.obtenerRegion().equals("00")) {
			if (SeguridadUtil.hasRole("ROLE_004")) {
				if(estadoActual.equals("2"))
					puedeAprobar=true;
			}

			if (SeguridadUtil.hasRole("ROLE_005")) {
				if(estadoActual.equals("4"))
					puedeAprobar=true;
			}

			if (SeguridadUtil.hasRole("ROLE_003")) {
				if(estadoActual.equals("5"))
					puedeAprobar=true;
			}


		}

		if(!SeguridadUtil.obtenerRegion().equals("00")) {
			if (SeguridadUtil.hasRole("ROLE_005")) {
				if(estadoActual.equals("2"))
					puedeAprobar=true;
			}

			if (SeguridadUtil.hasRole("ROLE_002")) {
				if(estadoActual.equals("5"))
					puedeAprobar=true;
			}


		}
		return puedeAprobar;
	}


}
