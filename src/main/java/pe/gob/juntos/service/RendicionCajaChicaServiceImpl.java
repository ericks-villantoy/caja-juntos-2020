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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;

import pe.gob.juntos.entity.ConfiguracionCajaChica;
import pe.gob.juntos.entity.ControlCierreMesCajaChica;
import pe.gob.juntos.entity.DetalleRendicion;
import pe.gob.juntos.entity.DetalleRendicionCajaChica;
import pe.gob.juntos.entity.QAdministrado;
import pe.gob.juntos.entity.QDetalleRendicion;
import pe.gob.juntos.entity.QRendicion;
import pe.gob.juntos.entity.QSede;
import pe.gob.juntos.entity.RendicionCajaChica;
import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.request.ReaperturaCierreCacjaChicaRequest;
import pe.gob.juntos.request.RendicionCajaChicaRequest;
import pe.gob.juntos.request.RendicionConsultaAdministradoRequest;
import pe.gob.juntos.request.RendicionDetalleCajaChicaRequest;
import pe.gob.juntos.response.ControlCierreMesCajaChicaResponse;
import pe.gob.juntos.response.DetalleRendicionResponse;
import pe.gob.juntos.response.RendicionCajaChicaResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.respository.ConfiguracionCajaChicaRepository;
import pe.gob.juntos.respository.ControlCierreMesCajaChicaRepository;
import pe.gob.juntos.respository.DetalleRendicionRepository;
import pe.gob.juntos.respository.RendicionCajaChicaRepository;
import pe.gob.juntos.respository.SedeRepository;
import pe.gob.juntos.util.FechaUtil;
import pe.gob.juntos.util.SeguridadUtil;

@Service("rendicionCajaChicaService")
public class RendicionCajaChicaServiceImpl implements RendicionCajaChicaService {

	private static final Logger logger = LoggerFactory.getLogger(RendicionCajaChicaServiceImpl.class);

	@Autowired
	EntityManager em;

	@Autowired
	private RendicionCajaChicaRepository rendicionCajaChicaRepository;

	@Autowired
	private SedeRepository sedeRepository;

	@Autowired
	private DetalleRendicionRepository detalleRendicionRepository;

	@Autowired
	private ConfiguracionCajaChicaRepository configuracionCajaChicaRepository;

	@Autowired
	private ControlCierreMesCajaChicaRepository controlCierreMesCajaChicaRepository;

	@Override
	public List<DetalleRendicionResponse> listarRendicionesRegionAprobadas(RendicionConsultaAdministradoRequest req) {
		List<DetalleRendicion> listadoDetalleRendicion = new ArrayList<DetalleRendicion>();
		List<DetalleRendicionResponse> listadoRendicionesDetallesAdministrado = new ArrayList<DetalleRendicionResponse>();
		String codigoRegion = SeguridadUtil.obtenerRegion();

		try {
			QDetalleRendicion detalleRendicion = QDetalleRendicion.detalleRendicion;
			QRendicion rendicion = QRendicion.rendicion;
			QAdministrado administrado = QAdministrado.administrado;
			QSede sede = QSede.sede;

			JPAQuery<DetalleRendicion> query = new JPAQuery<DetalleRendicion>(em);

			StringExpression idSedeFiltro = sede.idSede;

			Predicate mConsultaSede = null;
			Predicate mConsultaRestrictiva = detalleRendicion.eliminado.eq("0")
					.and(detalleRendicion.vigente.eq("1"))
					.and(rendicion.eliminado.eq("0")
							.and(rendicion.vigente.eq("1")));

			Predicate mConsultaEstado = rendicion.estado.in("9");
			String[] listaEstado = {"1","2"};
			Predicate mConsultaEstadoEnvio = detalleRendicion.estadoEnvio.isNull().or(detalleRendicion.estadoEnvio.notIn(listaEstado));

			if(SeguridadUtil.obtenerRegion().equals("00")) {
				mConsultaSede = idSedeFiltro.eq(req.getIdSede());
			}

			if(!SeguridadUtil.obtenerRegion().equals("00")) {
				mConsultaSede = idSedeFiltro.eq(codigoRegion);
			}

			listadoDetalleRendicion = query.from(detalleRendicion)
					.join(detalleRendicion.rendicion, rendicion)
					.join(rendicion.administrado, administrado)
					.join(administrado.sede, sede)
					.distinct()
					.where(	 
							mConsultaSede, 
							mConsultaRestrictiva,
							mConsultaEstado,
							mConsultaEstadoEnvio
							)

					.fetch();

			/*listadoDetalleRendicion.forEach(obj -> {

				DetalleRendicionResponse ren = new DetalleRendicionResponse();
				BeanUtils.copyProperties(obj, ren);
				ren.setIdRendicion(obj.getRendicion().getIdRendicion());
				ren.setEstadoRendicion(obj.getRendicion().getEstado());
				listadoRendicionesDetallesAdministrado.add(ren);

			});*/

			for(DetalleRendicion dr : listadoDetalleRendicion) {
				DetalleRendicionResponse ren = new DetalleRendicionResponse();
				BeanUtils.copyProperties(dr, ren);
				ren.setIdRendicion(dr.getRendicion().getIdRendicion());
				ren.setEstadoRendicion(dr.getRendicion().getEstado());
				listadoRendicionesDetallesAdministrado.add(ren);
			}			

		} catch (Exception e) {
			logger.error("error completo listarRendicionesRegion: " + ExceptionUtils.getFullStackTrace(e));
		}

		return listadoRendicionesDetallesAdministrado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public RespuestaGeneralResponse enviarRendicionCajaChica(RendicionCajaChicaRequest req) {
		List<RendicionDetalleCajaChicaRequest> listadoRendicionCajachica = req.getListadoRendicionCajachica();
		List<DetalleRendicionCajaChica> listadoDetalleRendicionCajachica = new ArrayList<DetalleRendicionCajaChica>();

		RespuestaGeneralResponse response = new  RespuestaGeneralResponse();

		RendicionCajaChica rendicionCajaChicaSave = null;
		try {
			response.setExito(true);
			Sede sede = sedeRepository.findOne(req.getIdSede());
			if(listadoRendicionCajachica.size()<1) {
				response.setExito(false);
				response.setRespuesta("No se ha seleccionado detalles");
				return response;
			}

			/*Long totalImporte = listadoRendicionCajachica.stream()
					.mapToLong(det-> det.getImporte())
					.sum();*/

			Long totalImporte = 0L;

			for(RendicionDetalleCajaChicaRequest rd : listadoRendicionCajachica) {
				totalImporte = totalImporte + rd.getImporte();	
			}				

			String anio = FechaUtil.obtenerAnioActual();
			String mes = FechaUtil.obtenerMesActual();

			List<RendicionCajaChica> listadoRendicionMensual = rendicionCajaChicaRepository.listarRendicionesMensualesSede(anio, mes, sede.getIdSede());

			int envios = listadoRendicionMensual.size() + 1;

			RendicionCajaChica rendicionCajachica = new RendicionCajaChica();
			rendicionCajachica.setSede(sede);
			rendicionCajachica.setAnio(anio);
			rendicionCajachica.setMes(mes);
			rendicionCajachica.setImporte(totalImporte);
			rendicionCajachica.setUsuarioRendicionCajaChica(SeguridadUtil.obtenerUsuario());
			rendicionCajachica.setFechaRendicionCajaChica(new Date());
			rendicionCajachica.setNroEnvio(new Long(envios));
			rendicionCajachica.setEliminado("0");
			rendicionCajachica.setVigente("1");
			rendicionCajachica.setUsuarioCreacion(SeguridadUtil.obtenerUsuario());
			rendicionCajachica.setFechaCreacion(new Date());

			for(RendicionDetalleCajaChicaRequest rd : listadoRendicionCajachica) {

				if(rd.isMarcado()) {

					DetalleRendicion detalleRendicion = detalleRendicionRepository.findOne(rd.getIdDetalleRendicion());
					detalleRendicion.setEstadoEnvio("1");
					detalleRendicion.setUsuarioModificacion(SeguridadUtil.obtenerCodigoUsuario());
					DetalleRendicion detalleRendicionSave = detalleRendicionRepository.save(detalleRendicion);

					DetalleRendicionCajaChica detalleRendicionCajaChica = new DetalleRendicionCajaChica();
					detalleRendicionCajaChica.setDetalleRendicion(detalleRendicionSave);
					detalleRendicionCajaChica.setRendicionCajaChica(rendicionCajachica);
					detalleRendicionCajaChica.setEstado("1");
					detalleRendicionCajaChica.setEliminado("0");
					detalleRendicionCajaChica.setVigente("1");
					detalleRendicionCajaChica.setUsuarioCreacion(SeguridadUtil.obtenerUsuario());
					detalleRendicionCajaChica.setFechaCreacion(new Date());

					if(rd.isObservado()) {
						detalleRendicionCajaChica.setComentario(rd.getObservacionRendicion());
						detalleRendicionCajaChica.setEstado("2");

						detalleRendicionSave.setEstadoEnvio(null);
						detalleRendicionRepository.save(detalleRendicionSave);

					}
					listadoDetalleRendicionCajachica.add(detalleRendicionCajaChica);

				}	
			}			

			/*listadoRendicionCajachica.forEach(r->{
				if(r.isMarcado()) {
				DetalleRendicion detalleRendicion = detalleRendicionRepository.findOne(r.getIdDetalleRendicion());
				detalleRendicion.setEstadoEnvio("1");
				detalleRendicion.setUsuarioModificacion(SeguridadUtil.obtenerCodigoUsuario());
				DetalleRendicion detalleRendicionSave = detalleRendicionRepository.save(detalleRendicion);

				DetalleRendicionCajaChica detalleRendicionCajaChica = new DetalleRendicionCajaChica();
				detalleRendicionCajaChica.setDetalleRendicion(detalleRendicionSave);
				detalleRendicionCajaChica.setRendicionCajaChica(rendicionCajachica);
				detalleRendicionCajaChica.setEstado("1");
				detalleRendicionCajaChica.setEliminado("0");
				detalleRendicionCajaChica.setVigente("1");
				detalleRendicionCajaChica.setUsuarioCreacion(SeguridadUtil.obtenerUsuario());
				detalleRendicionCajaChica.setFechaCreacion(new Date());
				if(r.isObservado()) {
					detalleRendicionCajaChica.setComentario(r.getObservacionRendicion());
					detalleRendicionCajaChica.setEstado("2");

					detalleRendicionSave.setEstadoEnvio(null);
					detalleRendicionRepository.save(detalleRendicionSave);

				}
				listadoDetalleRendicionCajachica.add(detalleRendicionCajaChica);

				}

			});*/		

			rendicionCajachica.setListadoDetalleRendicionCajaChica(listadoDetalleRendicionCajachica);

			rendicionCajaChicaSave = rendicionCajaChicaRepository.save(rendicionCajachica);

			if(rendicionCajaChicaSave==null) {
				response.setExito(false);
				response.setRespuesta("Hubo problemas al registrar envio de rendicion");
				return response;
			}

		} catch (Exception e) {
			logger.error("error completo enviarRendicionCajaChica :" + ExceptionUtils.getFullStackTrace(e));
			response.setExito(false);
			response.setRespuesta("Hubo problemas al registrar envio de rendicion");
			return response;

		}

		return response;
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
			});*/
			
			for(RendicionCajaChica rc : listadoRendicionesCajaChica) {
				RendicionCajaChicaResponse resp = new RendicionCajaChicaResponse();
				BeanUtils.copyProperties(rc, resp);
				listadoRendicionesCajaChicaResponse.add(resp);
			}		

		} catch (Exception e) {
			logger.error("error completo listarRendicionesCajaChica: " + ExceptionUtils.getFullStackTrace(e));
		}

		return listadoRendicionesCajaChicaResponse;
	}

	@Override
	public ConfiguracionCajaChica obtenerConfiguracionSede(String idSede) {
		ConfiguracionCajaChica configuracion= configuracionCajaChicaRepository.buscarConfiguracion(idSede);
		return configuracion;
	}

	@Override
	public RespuestaGeneralResponse reaperturarCerrarCajaChica(ReaperturaCierreCacjaChicaRequest req) {

		ControlCierreMesCajaChica controlCierre = new ControlCierreMesCajaChica();
		RespuestaGeneralResponse response = new RespuestaGeneralResponse();
		Sede sede = sedeRepository.findOne(req.getIdSede());

		try {
			response.setExito(true);

			if(req.getAccion()==1) {
				controlCierre.setAnio(req.getAnio());
				controlCierre.setEstado("1");
				controlCierre.setSede(sede);
				controlCierre.setMes(req.getMes());
				controlCierre.setAnotacion(req.getObservacion());
				controlCierre.setUsuarioCreacion(SeguridadUtil.obtenerUsuario());
				controlCierre.setFechaCreacion(new Date());
				ControlCierreMesCajaChica controlCierreSave= controlCierreMesCajaChicaRepository.save(controlCierre);

				if(controlCierreSave==null) {
					response.setExito(false);
					response.setRespuesta("Hubo problemas al registrar reapertura o cierre de caja chica");
					return response;
				}
			}

			if(req.getAccion()==2) {
				ControlCierreMesCajaChica control = controlCierreMesCajaChicaRepository.buscarControlCierreAnioMes(req.getIdSede(), req.getAnio(), req.getMes());
				if(control==null) {
					response.setExito(false);
					response.setRespuesta("Hubo problemas al recuperar reapertura o cierre de caja chica");
					return response;
				}

				control.setEstado("2");
				control.setUsuarioModificacion(SeguridadUtil.obtenerUsuario());
				control.setFechaModificacion(new Date());
				ControlCierreMesCajaChica controlCierreSave= controlCierreMesCajaChicaRepository.save(controlCierre);

				if(controlCierreSave==null) {
					response.setExito(false);
					response.setRespuesta("Hubo problemas al registrar reapertura o cierre de caja chica");
					return response;
				}
			}


		} catch (Exception e) {
			logger.error("error completo reaperturarCerrarCajaChica: " + ExceptionUtils.getFullStackTrace(e));
			response.setExito(false);
			response.setRespuesta("Hubo problemas al registrar reapertura o cierre de caja chica");
			return response;
		}

		return response;
	}

	@Override
	public ControlCierreMesCajaChica obtenerControlCierreCajaChica(String idSede) {
		ControlCierreMesCajaChica control = controlCierreMesCajaChicaRepository.buscarControlCierre(idSede);
		return control;
	}

	@Override
	public List<ControlCierreMesCajaChicaResponse> listarHistorialCierreReapertura(
			ReaperturaCierreCacjaChicaRequest req) {

		List<ControlCierreMesCajaChicaResponse> listadoHistorialCierreReapertura = new ArrayList<ControlCierreMesCajaChicaResponse>();
		try {
			String idSede = req.getIdSede();
			Long anio = req.getAnio(); 
			Long mes = req.getMes();
			ControlCierreMesCajaChica control =  controlCierreMesCajaChicaRepository.buscarControlCierreAnioMes(idSede, anio, mes);
			if(control!=null) {
				String historial="";
				String accion="";
				switch (control.getEstado()) {
				case "1":
					accion = "CIERRE";
					break;

				case "2":
					accion = "REAPERTURA";
					break;
				}

				if(control.getUsuarioCreacion()!=null && control.getFechaCreacion()!=null)
					historial = "["+FechaUtil.ConvertirFechaDDMMYYYYHHmmss(control.getFechaCreacion())+ "] " + accion+ " "+ control.getUsuarioCreacion();

				if(control.getAnotacion()!=null)
					historial += "[OBS: " + control.getAnotacion() + " ]";


				ControlCierreMesCajaChicaResponse response = new ControlCierreMesCajaChicaResponse();
				response.setEstado(control.getEstado());
				response.setFechaCreacion(FechaUtil.ConvertirFechaDDMMYYYYHHmmss(control.getFechaCreacion()));
				response.setUsuarioCreacion(control.getUsuarioCreacion());
				response.setHistorial(historial);
				listadoHistorialCierreReapertura.add(response);


				if(control.getUsuarioModificacion()!=null) {
					ControlCierreMesCajaChicaResponse response1 = new ControlCierreMesCajaChicaResponse();
					response.setEstado(control.getEstado());
					response.setFechaCreacion(FechaUtil.ConvertirFechaDDMMYYYYHHmmss(control.getFechaModificacion()));
					response.setUsuarioCreacion(control.getUsuarioModificacion());
					response.setHistorial(historial);
					listadoHistorialCierreReapertura.add(response1);
				}	
			}

		} catch (Exception e) {
			logger.error("error completo listarHistorialCierreReapertura: " + ExceptionUtils.getFullStackTrace(e));
		}

		return listadoHistorialCierreReapertura;
	}



}
