package pe.gob.juntos.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;

import pe.gob.juntos.entity.Administrado;
import pe.gob.juntos.entity.Clase;
import pe.gob.juntos.entity.DetalleRendicion;
import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.entity.QAdministrado;
import pe.gob.juntos.entity.QRendicion;
import pe.gob.juntos.entity.QSede;
import pe.gob.juntos.entity.Rendicion;
import pe.gob.juntos.entity.RutaDetalleRendicion;
import pe.gob.juntos.entity.RutaSede;
import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.entity.VistaDatosComisionado;
import pe.gob.juntos.request.DetalleRendicionRequest;
import pe.gob.juntos.request.DetalleRendicionTarifarioRequest;
import pe.gob.juntos.request.RendicionAdministradoRequest;
import pe.gob.juntos.request.RendicionConsultaAdministradoRequest;
import pe.gob.juntos.response.DetalleRendicionResponse;
import pe.gob.juntos.response.RendicionAdministradoResponse;
import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.response.RutaDetalleRendicionResponse;
import pe.gob.juntos.respository.AdministradoRepository;
import pe.gob.juntos.respository.ClaseRepository;
import pe.gob.juntos.respository.DetalleRendicionRepository;
import pe.gob.juntos.respository.EspecificaRepository;
import pe.gob.juntos.respository.RendicionRepository;
import pe.gob.juntos.respository.RutaRendicionDetalleRepository;
import pe.gob.juntos.respository.RutaSedeRepository;
import pe.gob.juntos.respository.SedeRepository;
import pe.gob.juntos.respository.VistaDatosComisionadoRepository;
import pe.gob.juntos.util.PdfUtil;
import pe.gob.juntos.util.SeguridadUtil;

@Service("rendicionAdministradoService")
public class RendicionAdministradoServiceImpl implements RendicionAdministradoService {

	private static final Logger logger = LoggerFactory.getLogger(RendicionAdministradoServiceImpl.class);
	
	@Autowired
	EntityManager em;
	
	@Autowired
	private ComunService comunService;
	
	@Autowired
	private RendicionRepository rendicionRepository;
	
	@Autowired
	private DetalleRendicionRepository detalleRendicionRepository;
	
	@Autowired
	private ClaseRepository claseRepository;
	
	@Autowired
	private EspecificaRepository especificaRepository;
	
	@Autowired
	private AdministradoRepository administradoRepository;
	
	@Autowired
	private RutaRendicionDetalleRepository rutaRendicionDetalleRepository;
	
	@Autowired
	private RutaSedeRepository rutaSedeRepository; 
	
	@Autowired
	private VistaDatosComisionadoRepository vistaDatosComisionadoRepository;
	
	@Autowired
	private SedeRepository sedeRepository;
	
	
	@Value("${sistema.download.path}")
	private String carpetaTemporalPDF;
	
	@Override
	public List<RendicionAdministradoResponse> listarRendicionesAdministrado(RendicionConsultaAdministradoRequest req) {
		
		List<Rendicion> listadoRendicion = new ArrayList<Rendicion>();
		List<RendicionAdministradoResponse> listadoRendicionesAdministrado = new ArrayList<RendicionAdministradoResponse>();
		try {
			QRendicion rendicion = QRendicion.rendicion;
			QAdministrado administrado = QAdministrado.administrado;
			QSede sede = QSede.sede;
			
			JPAQuery<Rendicion> query = new JPAQuery<Rendicion>(em);

			DateTimePath<Date> fechaInicioDesdeFiltro = rendicion.fechaInicio;
			StringExpression idSedeFiltro = sede.idSede;
			
			
			Predicate mConsultaFechaInicio = null;
			Predicate mConsultaSede = null;
			Predicate mConsultaRestrictiva = rendicion.eliminado.eq("0").and(rendicion.vigente.eq("1"));
			Predicate mConsutalUsuario = administrado.idAdministrado.eq(SeguridadUtil.obtenerCodigoUsuario());
						
			mConsultaSede = idSedeFiltro.eq(SeguridadUtil.obtenerRegion());	
			
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
										mConsutalUsuario
									  )

								.fetch();

			/*listadoRendicion.forEach(obj -> {

				RendicionAdministradoResponse ren = new RendicionAdministradoResponse();
				BeanUtils.copyProperties(obj, ren);
				if(obj.getListadoDetallesRendicion().size()>0)
					ren.setPuedeEnviar(true);
				listadoRendicionesAdministrado.add(ren);
				
			});*/
			
			for(Rendicion r : listadoRendicion) {
				
				RendicionAdministradoResponse ren = new RendicionAdministradoResponse();
				BeanUtils.copyProperties(r, ren);
				
				if(r.getListadoDetallesRendicion().size()>0)
					ren.setPuedeEnviar(true);
				
				listadoRendicionesAdministrado.add(ren);
			}		
			
		} catch (Exception e) {
			logger.error("error completo listarRendicionesAdministrado: " + ExceptionUtils.getFullStackTrace(e));
		}
		
		return listadoRendicionesAdministrado;
	}
	
	
	@Override
	public RespuestaGeneralResponse registrarDetalleRendicionTarifarioAdministrado(DetalleRendicionTarifarioRequest req) {
		RutaDetalleRendicion rutaDetalleRendicion = new RutaDetalleRendicion();
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		RutaSede rutaSede = null;
		DetalleRendicion detalleRendicionSave=null;
		String user = SeguridadUtil.obtenerUsuario();
		try {
			mRespuesta.setExito(true);
			if (req.isEliminar()) {
				mRespuesta = this.eliminarRendicionDetalleTarifarioAdministrado(req);
				return mRespuesta;
			}
			
			if (req.getIdRutaSede()==null) {
				mRespuesta.setExito(false);				
				mRespuesta.setRespuesta("No se pudo recuperar tarifario");
				return mRespuesta;

			}
			
			if(req.getIdRendicion()==null) {
				Rendicion rendicion = new Rendicion();
				BeanUtils.copyProperties(req.getRendicionReq(), rendicion);
				
				VistaDatosComisionado vistaComisionado= vistaDatosComisionadoRepository.obtenerComisionadoUsuario(user);
				
				if(vistaComisionado!=null)
					rendicion.setSedeUnidad(vistaComisionado.getCodigoUnidad());
				
				String mExtencion = ".pdf";
				String mNuevoNombreArchivo = "DOC_DETA_ITEM_"+user+"_REG_"+ SeguridadUtil.obtenerRegion()+"_"+UUID.randomUUID().toString().substring(0,5) +mExtencion;
				List<String> listadoPdfBase64 = req.getRendicionReq().getListadoDocumentoArchivo();
				mRespuesta = this.cargarArchivoPDF(mNuevoNombreArchivo, listadoPdfBase64);
				
				if(!mRespuesta.isExito()) {
					return mRespuesta;
				}
				rendicion.setDocumentoReferencia(listadoPdfBase64.size()>0?mNuevoNombreArchivo:"");
				rendicion.setFechaFin(new Date());
				rendicion.setUsuarioCreacion(SeguridadUtil.obtenerCodigoUsuario());
				rendicion.setFechaCreacion(new Date());
				//rendicion.setImporteTotal(new Long(0));
				rendicion.setImporteTotal(0);
				rendicion.setEstado("1");
				rendicion.setPagado("N");
				
				Rendicion rendicionSave = rendicionRepository.save(rendicion);
				
				if (rendicionSave==null) {
					mRespuesta.setExito(false);					
					mRespuesta.setRespuesta("Inconveniente registrando rendicion");
					return mRespuesta;
	
				}
				
				if(req.getIdDetalleRendicion()==null) {
					DetalleRendicion detalleRendicion = new DetalleRendicion();
					BeanUtils.copyProperties(req.getDetalleRendicionReq(), detalleRendicion);
					Especifica especifica = especificaRepository.findOne(req.getDetalleRendicionReq().getIdEspecifica());
					Clase clase = claseRepository.findOne(req.getDetalleRendicionReq().getIdClase());
					detalleRendicion.setRendicion(rendicion);
					detalleRendicion.setClase(clase);
					detalleRendicion.setEspecifica(especifica);
					detalleRendicion.setFecha(new Date());
					detalleRendicion.setUsuarioCreacion(SeguridadUtil.obtenerCodigoUsuario());
					detalleRendicion.setFechaCreacion(new Date());
					detalleRendicionSave = detalleRendicionRepository.save(detalleRendicion);
					
					if(detalleRendicionSave==null) {
						mRespuesta.setExito(false);						
						mRespuesta.setRespuesta("Inconveniente registrando item");
						return mRespuesta;
					}
				}
				
			}else {
				if(req.getIdDetalleRendicion()!=null) {
					DetalleRendicion detalleRendicion= detalleRendicionRepository.findOne(req.getIdDetalleRendicion());
					BeanUtils.copyProperties(req.getDetalleRendicionReq(), detalleRendicion);
					detalleRendicionSave = detalleRendicionRepository.save(detalleRendicion);
					
					if(detalleRendicionSave==null) {
						mRespuesta.setExito(false);						
						mRespuesta.setRespuesta("Inconveniente recuperando detalle");
						return mRespuesta;
					}
				}else {
					Rendicion rendicion = rendicionRepository.findOne(req.getIdRendicion());
					DetalleRendicion detalleRendicion = new DetalleRendicion();
					BeanUtils.copyProperties(req.getDetalleRendicionReq(), detalleRendicion);
					Especifica especifica = especificaRepository.findOne(req.getDetalleRendicionReq().getIdEspecifica());
					Clase clase = claseRepository.findOne(req.getDetalleRendicionReq().getIdClase());
					detalleRendicion.setRendicion(rendicion);
					detalleRendicion.setClase(clase);
					detalleRendicion.setEspecifica(especifica);
					detalleRendicion.setFecha(new Date());
					detalleRendicion.setUsuarioCreacion(SeguridadUtil.obtenerCodigoUsuario());
					detalleRendicion.setFechaCreacion(new Date());
					detalleRendicionSave = detalleRendicionRepository.save(detalleRendicion);
					
					if(detalleRendicionSave==null) {
						mRespuesta.setExito(false);						
						mRespuesta.setRespuesta("Inconveniente registrando item");
						return mRespuesta;
					}
				}
				
			}
			
				List<RutaDetalleRendicion> listadoRutaDetalleRendicion = new ArrayList<RutaDetalleRendicion>();
			
				rutaSede = rutaSedeRepository.findOne(req.getIdRutaSede());
				
				rutaDetalleRendicion.setRutaSede(rutaSede);
				rutaDetalleRendicion.setImporte(new Long(rutaSede.getTarifa()));
				rutaDetalleRendicion.setEstado("A");
				rutaDetalleRendicion.setEliminado("0");
				rutaDetalleRendicion.setVigente("1");
				rutaDetalleRendicion.setUsuarioCreacion(SeguridadUtil.obtenerCodigoUsuario());
				rutaDetalleRendicion.setFechaCreacion(new Date());
				listadoRutaDetalleRendicion.add(rutaDetalleRendicion);
				rutaDetalleRendicion.setDetalleRendicion(detalleRendicionSave);
				RutaDetalleRendicion rutaDetalleRendicionSave = rutaRendicionDetalleRepository.save(rutaDetalleRendicion);
				
				if (rutaDetalleRendicionSave==null) {
					mRespuesta.setExito(false);					
					mRespuesta.setRespuesta("Inconveniente registrando rendicion");
					return mRespuesta;
	
				}
				mRespuesta.setIdDetalleRendicion(detalleRendicionSave.getIdDetalleRendicion());
				
				/////////////////////////////////////////////////
				//Actualizar importe totalizado de la rendición
				/////////////////////////////////////////////////
				
				Rendicion rendicionFinal = rendicionRepository.findOne(detalleRendicionSave.getRendicion().getIdRendicion());
				rendicionFinal.setImporteTotal((sumarizarImporteTotalizado(detalleRendicionSave.getRendicion().getIdRendicion())));			
				rendicionFinal = rendicionRepository.save(rendicionFinal);
				
				/*
				Rendicion rendicionFinal = rendicionRepository.findOne(detalleRendicionSave.getRendicion().getIdRendicion());
				List<DetalleRendicion> list = rendicionFinal.getListadoDetallesRendicion();
				float importeSum =0;
				float importeVale = 0;
				
				for (DetalleRendicion detalleRendicion2 : list) {
					if(detalleRendicion2.getEliminado().equals("0") && detalleRendicion2.getVigente().equals("1")) {
						if(detalleRendicion2.getClase().getIdClase().equals(7L)) {
							importeVale = importeVale+detalleRendicion2.getImporte();
						}
						else {
							importeSum = importeSum+detalleRendicion2.getImporte();
						}
					}
				}
				
				if(importeSum>0)
					rendicionFinal.setImporteTotal(importeSum);
				else
					rendicionFinal.setImporteTotal(importeVale);
				
				rendicionFinal = rendicionRepository.save(rendicionFinal);*/
				
				
			
		} catch (Exception e) {
			logger.error("error completo registrarDetalleRendicionAdministrado: " + ExceptionUtils.getFullStackTrace(e));
			mRespuesta.setExito(false);			
			mRespuesta.setRespuesta("Inconveniente registrando Ruta de Sede");
			return mRespuesta;
		}
		return mRespuesta;
	}

	
	
	@Override
	public RespuestaGeneralResponse registrarRendicionAdministrado(RendicionAdministradoRequest req) {
		Rendicion rendicion = new Rendicion();
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		List<DetalleRendicion> listadoDetalleRendiciones = new ArrayList<DetalleRendicion>();
		String usuario = SeguridadUtil.obtenerUsuario();
		String codUsuario = SeguridadUtil.obtenerCodigoUsuario();
		try {
			mRespuesta.setExito(true);
			
			String mExtencion = ".pdf";
			String mNuevoNombreArchivo = "DOC_DETA_"+usuario+"_REG_"+ SeguridadUtil.obtenerRegion()+"_"+UUID.randomUUID().toString().substring(0,5) +mExtencion;
			
			if (req.isEliminar()) {
				mRespuesta = this.eliminarRendicionAdministrado(req);
				return mRespuesta;
			}
			
			if (req.isEnviarAprobar()) {
				mRespuesta = this.enviarAprobarRendicionAdministrado(req);
				return mRespuesta;
			}
		
			if(req.getIdRendicion()!=null)
				rendicion = rendicionRepository.findOne(req.getIdRendicion());
			
			if(rendicion.getAdministrado()==null) {
					Administrado administrado = administradoRepository.findOne(codUsuario);
					rendicion.setAdministrado(administrado);
			}
			BeanUtils.copyProperties(req, rendicion);
			
			List<String> listadoPdfBase64 = req.getListadoDocumentoArchivo();
			if(listadoPdfBase64 !=null && listadoPdfBase64.size()>0) {
			
			
				mRespuesta = this.cargarArchivoPDF(mNuevoNombreArchivo, listadoPdfBase64);
				
				if(!mRespuesta.isExito()) {
					return mRespuesta;
				}
			
				rendicion.setDocumentoReferencia(mNuevoNombreArchivo);
				
			}
			
				
				
				//Long totalImporte = new Long(0);
				//float totalImporte = 0;
				float importeSum =0;
				float importeVale = 0;
				
				if(req.getListadoDetallesRendicion() !=null && req.getListadoDetallesRendicion().size()>0) {
					List<DetalleRendicionRequest> listadoDetallesRendicion = req.getListadoDetallesRendicion();
				/*
					totalImporte = listadoDetallesRendicion.stream()
										.mapToLong(det-> det.getImporte())
										.sum();*/					
					
					for (DetalleRendicionRequest det : listadoDetallesRendicion) {
							DetalleRendicion deta = new DetalleRendicion();
							BeanUtils.copyProperties(det, deta);
							if(det.getIdDetalleRendicion()!=null)
								deta= detalleRendicionRepository.findOne(det.getIdDetalleRendicion());
							
							if(det.getIdEspecifica()!=null) {
								Especifica especifica = especificaRepository.findOne(det.getIdEspecifica());
								deta.setEspecifica(especifica);
							}
							if(det.getIdClase()!=null) {
								Clase clase = claseRepository.findOne(det.getIdClase());
								deta.setClase(clase);
							}
							deta.setRendicion(rendicion);
							deta.setVigente("1");
							deta.setEliminado("0");
							deta.setUsuarioCreacion(SeguridadUtil.obtenerCodigoUsuario());
							deta.setFechaCreacion(new Date());
							listadoDetalleRendiciones.add(deta);
							
							
							if(deta.getEliminado().equals("0") && deta.getVigente().equals("1")) {
								if(deta.getClase().getIdClase().equals(7L)) {
									importeVale = importeVale+deta.getImporte();
								}
								else {
									importeSum = importeSum+deta.getImporte();
								}
							}
							
					};
					
					rendicion.setListadoDetallesRendicion(listadoDetalleRendiciones);	
				}
				
				VistaDatosComisionado vistaComisionado= vistaDatosComisionadoRepository.obtenerComisionadoUsuario(usuario);
				if(vistaComisionado!=null)
					rendicion.setSedeUnidad(vistaComisionado.getCodigoUnidad());
				
				//rendicion.setImporteTotal(totalImporte);
				if(importeSum>0)
					rendicion.setImporteTotal(importeSum);
				else
					rendicion.setImporteTotal(importeVale);
				
				rendicion.setEliminado("0");
				rendicion.setVigente("1");
				rendicion.setEstado("1");
				rendicion.setFechaFin(new Date());
				rendicion.setUsuarioCreacion(SeguridadUtil.obtenerCodigoUsuario());
				rendicion.setFechaCreacion(new Date());
				rendicion.setPagado("N");

				Sede sedeRen =sedeRepository.findOne(SeguridadUtil.obtenerRegion());
				rendicion.setSede(sedeRen);
								
				Rendicion rendicionSave = rendicionRepository.save(rendicion);
				
				if (rendicionSave==null) {
					mRespuesta.setExito(false);
					mRespuesta.setRespuesta("Inconveniente registrando rendicion");
					return mRespuesta;
	
				}
			
				mRespuesta.setIdRendicion(rendicionSave.getIdRendicion());
			
		} catch (Exception e) {
			logger.error("error completo registrarTarifario: " + ExceptionUtils.getFullStackTrace(e));
			mRespuesta.setExito(false);
			mRespuesta.setRespuesta("Inconveniente registrando Ruta de Sede");
			return mRespuesta;
		}
		return mRespuesta;
	}
	
	
	@Override
	public RendicionAdministradoResponse obtenerRendicion(Long idRendicion) {
		RendicionAdministradoResponse rendicionResponse = new RendicionAdministradoResponse();
		List<DetalleRendicionResponse> listadoDetallesRendicion = new ArrayList<DetalleRendicionResponse>();
		
		Rendicion rendicion = new Rendicion(); 
		try {
			
			String codUsuario = SeguridadUtil.obtenerCodigoUsuario();
			
			Administrado administrado = administradoRepository.findOne(codUsuario);
			
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
				}
			}
			
			rendicionResponse.setListadoDetallesRendicion(listadoDetallesRendicion);
			
			rendicionResponse.setAdministrado(administrado);
			
		} catch (Exception e) {
			logger.error("error completo obtenerRendicion: " + ExceptionUtils.getFullStackTrace(e));
		}
		
		
		return rendicionResponse;
	}

	
	
	
	
	
	@Override
	public List<Especifica> listarEspecificaGasto() {
		List<Especifica> listadoEspecificaGasto = (List<Especifica>) especificaRepository.findAll();
		return listadoEspecificaGasto;
	}

	
	
	@Override
	public List<Clase> listarTipoClase() {
		List<Clase> listadoTipoClase = (List<Clase>) claseRepository.findAll();
		return listadoTipoClase;
	}

	
	
	
	@Override
	public RespuestaGeneralResponse registrarDetalleRendicionAdministrado(DetalleRendicionRequest req) {
		
		DetalleRendicion detalleRendicion = new DetalleRendicion();
		Rendicion rendicionSave = null;
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		String usuario = SeguridadUtil.obtenerUsuario();
		String codUsuario = SeguridadUtil.obtenerCodigoUsuario();
		String mExtencion = ".pdf";
		String mNuevoNombreArchivoRendicion = "DOC_DETA_ITEM_"+usuario+"_REG_"+ SeguridadUtil.obtenerRegion()+"_"+UUID.randomUUID().toString().substring(0,5) +mExtencion;
		String mNuevoNombreArchivoDetalle = "DOC_DETA_"+usuario+"_REG_"+ SeguridadUtil.obtenerRegion()+"_"+UUID.randomUUID().toString().substring(0,5) +mExtencion;
		
		try {
			mRespuesta.setExito(true);
			if (req.isEliminar()) {
				mRespuesta = this.eliminarDetalleRendicion(req);
				return mRespuesta;
			}
			
			if(req.getIdDetalleRendicion()!=null)
				detalleRendicion = detalleRendicionRepository.findOne(req.getIdDetalleRendicion());
			
			if(req.getIdRendicion()!=null) {
				rendicionSave = rendicionRepository.findOne(req.getIdRendicion());
				
			}else {
				Rendicion rendicion = new Rendicion();
				
				BeanUtils.copyProperties(req.getRendicionReq(), rendicion);
				
				
				List<String> listadoPdfBase64Rend = req.getRendicionReq().getListadoDocumentoArchivo();
				
				BeanUtils.copyProperties(req, detalleRendicion);
				if(listadoPdfBase64Rend !=null && listadoPdfBase64Rend.size()>0) {
					mRespuesta = this.cargarArchivoPDF(mNuevoNombreArchivoRendicion, listadoPdfBase64Rend);
					
					if(!mRespuesta.isExito()) {
						return mRespuesta;
					}
					rendicion.setDocumentoReferencia(mNuevoNombreArchivoRendicion);
				}
				
				VistaDatosComisionado vistaComisionado= vistaDatosComisionadoRepository.obtenerComisionadoUsuario(usuario);
				if(vistaComisionado!=null)
					rendicion.setSedeUnidad(vistaComisionado.getCodigoUnidad());
				
				rendicion.setFechaFin(new Date());
				//rendicion.setImporteTotal(new Long(0));
				rendicion.setImporteTotal(0);
				rendicion.setEstado("1");
				rendicion.setUsuarioCreacion(SeguridadUtil.obtenerCodigoUsuario());
				rendicion.setFechaCreacion(new Date());
				rendicion.setPagado("N");
				
				if(rendicion.getAdministrado()==null) {
					Administrado administrado = administradoRepository.findOne(codUsuario);
					rendicion.setAdministrado(administrado);
				}
				Sede sede = comunService.obtenerSede(SeguridadUtil.obtenerRegion());
				rendicion.setSede(sede);
				rendicionSave = rendicionRepository.save(rendicion);
				
				if (rendicionSave==null) {
					mRespuesta.setExito(false);
					mRespuesta.setRespuesta("Inconveniente registrando rendicion");
					return mRespuesta;
	
				}
			}
			
			
			List<String> listadoPdfBase64 = req.getListadoDocumentoArchivo();
			
			BeanUtils.copyProperties(req, detalleRendicion);
			if(listadoPdfBase64 !=null && listadoPdfBase64.size()>0) {
				mRespuesta = this.cargarArchivoPDF(mNuevoNombreArchivoDetalle, listadoPdfBase64);
				
				if(!mRespuesta.isExito()) {
					return mRespuesta;
				}
				detalleRendicion.setDocumentoReferencia(mNuevoNombreArchivoDetalle);
			}
			
			Especifica especifica = especificaRepository.findOne(req.getIdEspecifica());
			Clase clase = claseRepository.findOne(req.getIdClase());
			detalleRendicion.setEspecifica(especifica);
			detalleRendicion.setClase(clase);
			detalleRendicion.setRendicion(rendicionSave);
			
			detalleRendicion.setVigente("1");
			detalleRendicion.setEliminado("0");
			detalleRendicion.setUsuarioCreacion(SeguridadUtil.obtenerCodigoUsuario());
			detalleRendicion.setFechaCreacion(new Date());
			DetalleRendicion detalleRendicionSave = detalleRendicionRepository.save(detalleRendicion);
			
			if(detalleRendicionSave==null) {
				mRespuesta.setExito(false);
				mRespuesta.setRespuesta("Inconveniente registrando item");
				return mRespuesta;
			}
			
			////////////////////////////////////////////////
			//Actualizar importe totalizado de la rendición
			////////////////////////////////////////////////
			Rendicion rendicionFinal = rendicionRepository.findOne(rendicionSave.getIdRendicion());
			rendicionFinal.setImporteTotal((sumarizarImporteTotalizado(rendicionSave.getIdRendicion())));			
			rendicionFinal = rendicionRepository.save(rendicionFinal);
			
			mRespuesta.setIdDetalleRendicion(detalleRendicionSave.getIdDetalleRendicion());
			mRespuesta.setIdRendicion(rendicionSave.getIdRendicion());
			
		} catch (Exception e) {
			logger.error("error completo registrarDetalleRendicionAdministrado: " + ExceptionUtils.getFullStackTrace(e));
		}
		
		
		return mRespuesta;
	}
	
	public float sumarizarImporteTotalizado(Long idRendicion) {
	
		Rendicion rendicionFinal = rendicionRepository.findOne(idRendicion);
		List<DetalleRendicion> list = rendicionFinal.getListadoDetallesRendicion();
		float importeSum =0;
		float importeVale = 0;
		
		for (DetalleRendicion detalleRendicion2 : list) {
			if(detalleRendicion2.getEliminado().equals("0") && detalleRendicion2.getVigente().equals("1")) {
				if(detalleRendicion2.getClase().getIdClase().equals(7L)) {
					importeVale = importeVale+detalleRendicion2.getImporte();
				}
				else {
					importeSum = importeSum+detalleRendicion2.getImporte();
				}
			}
		}
		
		if(importeSum>0)
			return importeSum;
		else
			return importeVale;			
		
	}
	
	
	
	@Override
	public List<RutaSede> listarRutaSede() {
		
		String codigoSede = SeguridadUtil.obtenerRegion();
		
		List<RutaSede> listadoRutaSede = rutaSedeRepository.obtenerTarifarioSede(codigoSede);
		return listadoRutaSede;
	}
	
	@Override
	public List<RutaDetalleRendicionResponse> listarDetalleTarifarioRendicion(DetalleRendicionTarifarioRequest req) {
		
		List<RutaDetalleRendicionResponse> listadoResponse = new ArrayList<RutaDetalleRendicionResponse>();
		
		List<RutaDetalleRendicion> listadoDetalleTarifarioRendicion = rutaRendicionDetalleRepository.obtenerRutaRendicionDetallePorDetalle(req.getIdDetalleRendicion());
		
		/*listadoDetalleTarifarioRendicion.forEach(r->{
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
	
	
	private RespuestaGeneralResponse eliminarDetalleRendicion(DetalleRendicionRequest req) {
		RespuestaGeneralResponse response = new RespuestaGeneralResponse();
		
		try {
			response.setExito(true);
			if (req.getIdDetalleRendicion() == null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente recuperando el identificador de item a dar de baja");
				return response;
			}
			DetalleRendicion detalleRendicion = detalleRendicionRepository.findOne(req.getIdDetalleRendicion());
			
			if(detalleRendicion==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente recuperando la item de la rendicion a dar de baja");
				return response;
			}
			
			detalleRendicion.setEliminado("1");
			detalleRendicion.setUsuarioModificacion("deseguridad");
			detalleRendicion.setFechaModificacion(new Date());
			DetalleRendicion detalleRendicionSave = detalleRendicionRepository.save(detalleRendicion);
			
			if(detalleRendicionSave==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente eliminado registro");
				return response;
			}
			
			////////////////////////////////////////////////
			//Actualizar importe totalizado de la rendición
			////////////////////////////////////////////////
			Rendicion rendicionFinalAct = rendicionRepository.findOne(detalleRendicion.getRendicion().getIdRendicion());
			rendicionFinalAct.setImporteTotal((sumarizarImporteTotalizado(detalleRendicion.getRendicion().getIdRendicion())));			
			rendicionFinalAct = rendicionRepository.save(rendicionFinalAct);
			
			
		} catch (Exception e) {
			logger.error("error completo eliminarDetalleRendicion: " + ExceptionUtils.getFullStackTrace(e));
			response.setExito(false);
			response.setRespuesta("Inconveniente eliminado registro");
			return response;
		}
		
		return response;
	}
	
	
	private RespuestaGeneralResponse eliminarRendicionDetalleTarifarioAdministrado(DetalleRendicionTarifarioRequest req) {
		RespuestaGeneralResponse response = new RespuestaGeneralResponse();
		
		try {
			response.setExito(true);
			if (req.getIdRutaRendicionDetalle() == null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente recuperando el identificador del tarifario a dar de baja");
				return response;
			}
			RutaDetalleRendicion rutaDetalleRendicion = rutaRendicionDetalleRepository.findOne(req.getIdRutaRendicionDetalle());
			
			if(rutaDetalleRendicion==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente recuperando el tarifario del detalle de la rendicion a dar de baja");
				return response;
			}
			
			rutaDetalleRendicion.setEliminado("1");
			rutaDetalleRendicion.setUsuarioModificacion("deseguridad");
			rutaDetalleRendicion.setFechaModificacion(new Date());
			RutaDetalleRendicion rutaDetalleRendicionSave = rutaRendicionDetalleRepository.save(rutaDetalleRendicion);
			
			if(rutaDetalleRendicionSave==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente eliminado registro");
				return response;
			}
			
		} catch (Exception e) {
			logger.error("error completo eliminarRendicionDetalleTarifarioAdministrado: " + ExceptionUtils.getFullStackTrace(e));
			response.setExito(false);
			response.setRespuesta("Inconveniente eliminado registro");
			return response;
		}
		
		return response;
	}

	
	private RespuestaGeneralResponse eliminarRendicionAdministrado(RendicionAdministradoRequest req) {
		RespuestaGeneralResponse response = new RespuestaGeneralResponse();
		
		try {
			response.setExito(true);
			if (req.getIdRendicion() == null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente registrando identificador de la rendicion a dar de baja");
				return response;
			}
			Rendicion rendicion = rendicionRepository.findOne(req.getIdRendicion());
			
			if(rendicion==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente recuperando la rendicion del administrado a dar de baja");
				return response;
			}
			
			rendicion.setEliminado("1");
			rendicion.setUsuarioModificacion("deseguridad");
			rendicion.setFechaModificacion(new Date());
			Rendicion rendicionSave = rendicionRepository.save(rendicion);
			
			if(rendicionSave==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente eliminado registro");
				return response;
			}
			
			
			//Eliminar detalles de rendición			 
			List<DetalleRendicion> listDetalles = rendicion.getListadoDetallesRendicion();			
			for (DetalleRendicion detRendicion : listDetalles) {
				detRendicion.setEliminado("1");
				detRendicion.setUsuarioModificacion("deseguridad");
				detRendicion.setFechaModificacion(new Date());
				DetalleRendicion detalleRendicionSave = detalleRendicionRepository.save(detRendicion);
			}
			
			
		} catch (Exception e) {
			logger.error("error completo eliminarTarifarioSede: " + ExceptionUtils.getFullStackTrace(e));
			response.setExito(false);
			response.setRespuesta("Inconveniente eliminado registro");
			return response;
		}
		
		return response;
	}
	
	private RespuestaGeneralResponse enviarAprobarRendicionAdministrado(RendicionAdministradoRequest req) {
		RespuestaGeneralResponse response = new RespuestaGeneralResponse();
		
		try {
			response.setExito(true);
			if (req.getIdRendicion() == null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente registrando identificador de la rendicion a enviar para aprobacion");
				return response;
			}
			Rendicion rendicion = rendicionRepository.findOne(req.getIdRendicion());
			
			if(rendicion==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente recuperando la rendicion del administrado a enviar");
				return response;
			}
			rendicion.setFechaRendido(new Date());
			rendicion.setEstado("2");
			rendicion.setUsuarioModificacion(SeguridadUtil.obtenerCodigoUsuario());
			rendicion.setFechaModificacion(new Date());
			Rendicion rendicionSave = rendicionRepository.save(rendicion);
			
			if(rendicionSave==null) {
				response.setExito(false);
				response.setRespuesta("Inconveniente enviando registro");
				return response;
			}
			
		} catch (Exception e) {
			logger.error("error completo enviarAprobarRendicionAdministrado: " + ExceptionUtils.getFullStackTrace(e));
			response.setExito(false);
			response.setRespuesta("Inconveniente enviando registro");
			return response;
		}
		
		return response;
	}
	

	private boolean unirPdf(List<String> listadoPdfBase64, String rutaOutput){
		boolean rutaPDFUnido =false;
		
		List<InputStream > listadoArchivos = new ArrayList<InputStream>();
		
		try {
			
			if(listadoPdfBase64.size()>1){
				for (String pdfB64 : listadoPdfBase64) {
					
					String[] ArrayB64 = pdfB64.split(",");
					String tipo = ArrayB64[0];
					String arc64 = ArrayB64[1];
						
						if(tipo.equals("data:application/pdf;base64")){
							
							byte[] filePDF= BaseEncoding.base64().decode(arc64);
							
							InputStream is = new ByteArrayInputStream(filePDF);
							
							listadoArchivos.add(is);
						}
				
				}
			}
		
			OutputStream output = new FileOutputStream(rutaOutput);
			
			PdfUtil.concatPDFs(listadoArchivos, output, false);
			
			rutaPDFUnido=true;
			
		} catch (Exception e) {
			e.printStackTrace();
			rutaPDFUnido=false;
			return rutaPDFUnido;
		}
		
		
		
		return rutaPDFUnido;
	}

	
	
	
	private RespuestaGeneralResponse cargarArchivoPDF(String mNuevoNombreArchivo, List<String> listadoPdfBase64) {
		RespuestaGeneralResponse mRespuesta = new RespuestaGeneralResponse();
		
		try {
			mRespuesta.setExito(true);
			String mRutaArchivoLocal = carpetaTemporalPDF+mNuevoNombreArchivo;
			boolean unio=false;
			if(listadoPdfBase64!=null){
				
				
				if (listadoPdfBase64.size() > 1) {
					unio = this.unirPdf(listadoPdfBase64, mRutaArchivoLocal);
					
					if(!unio){
						mRespuesta.setExito(false);
						mRespuesta.setRespuesta("Inconvenientes para unir PDFs del documento a registrar");
						return mRespuesta;
					}
				
				}else {
					String[] ArrayBase64 =  listadoPdfBase64.get(0).split(",");
					String tipoDato = ArrayBase64[0];
					String archivoBase64 = ArrayBase64[1];

					if (tipoDato.equals("data:application/pdf;base64")) {

						byte[] filePDF = BaseEncoding.base64().decode(archivoBase64);

						File archivoPDF = new File(mRutaArchivoLocal);
						
						logger.info("iniciando copia archivo");
						Files.write(filePDF, archivoPDF);
						
						if(!archivoPDF.exists()) {
							mRespuesta.setExito(false);
							mRespuesta.setRespuesta("Archivo no se pudo crear");
							return mRespuesta;
						}

					} else {
						mRespuesta.setExito(false);
						mRespuesta.setRespuesta("Archivo o archivos no son formatos pdfs");
						return mRespuesta;
					}

				}
			
			}
			
		} catch (Exception e) {
			logger.error("Error completo cargarArchivoPDF: " + ExceptionUtils.getFullStackTrace(e));
		}
		
		return mRespuesta;
	}
}
