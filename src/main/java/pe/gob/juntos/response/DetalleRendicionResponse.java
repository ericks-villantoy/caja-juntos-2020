package pe.gob.juntos.response;

import java.util.Date;
import java.util.List;

import pe.gob.juntos.entity.Clase;
import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.util.FechaUtil;

public class DetalleRendicionResponse {
	
	private Long idDetalleRendicion;
	private Long idRendicion;
	private String estadoRendicion;
	private String estadoRendicionDescripcion;
	private Especifica especifica;
	private Clase clase;
	private String tipoClase;
	private Date fecha;
	private String fechatxt=" ";
	private String serieRecibo;
	private String numeroRecibo;
	private String rucEmpresa;
	private String razonSocial;
	private float importe;
	private String documentoReferencia;
	private int cantidadFolios;
	private String usuarioRegistraPagado;
	private Date fechaPagado;
	private String fechaPagadoStr;
	private String detalle;
	private String observacionRendicion;
	private boolean observado;
	private boolean marcado;
	private List<RutaDetalleRendicionResponse> listadoRutaDetalleRendicion;
	
	public Long getIdDetalleRendicion() {
		return idDetalleRendicion;
	}
	public void setIdDetalleRendicion(Long idDetalleRendicion) {
		this.idDetalleRendicion = idDetalleRendicion;
	}
	
	public Especifica getEspecifica() {
		return especifica;
	}
	public void setEspecifica(Especifica especifica) {
		this.especifica = especifica;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getSerieRecibo() {
		return serieRecibo;
	}
	public void setSerieRecibo(String serieRecibo) {
		this.serieRecibo = serieRecibo;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public String getRucEmpresa() {
		return rucEmpresa;
	}
	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public String getDocumentoReferencia() {
		return documentoReferencia;
	}
	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}
	public int getCantidadFolios() {
		return cantidadFolios;
	}
	public void setCantidadFolios(int cantidadFolios) {
		this.cantidadFolios = cantidadFolios;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public Long getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(Long idRendicion) {
		this.idRendicion = idRendicion;
	}
	public String getFechatxt() {
		if(this.getFecha()!=null)
			fechatxt = FechaUtil.ConvertirFechaDDMMYYYY(this.fecha);
		return fechatxt;
	}
	public void setFechatxt(String fechatxt) {
		this.fechatxt = fechatxt;
	}
	public String getTipoClase() {
		if(this.getClase()!=null) {
			tipoClase= this.clase.getDescripcionClase();
		}
		return tipoClase;
	}
	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
	}
	public List<RutaDetalleRendicionResponse> getListadoRutaDetalleRendicion() {
		return listadoRutaDetalleRendicion;
	}
	public void setListadoRutaDetalleRendicion(List<RutaDetalleRendicionResponse> listadoRutaDetalleRendicion) {
		this.listadoRutaDetalleRendicion = listadoRutaDetalleRendicion;
	}
	public String getUsuarioRegistraPagado() {
		return usuarioRegistraPagado;
	}
	public void setUsuarioRegistraPagado(String usuarioRegistraPagado) {
		this.usuarioRegistraPagado = usuarioRegistraPagado;
	}
	public Date getFechaPagado() {
		return fechaPagado;
	}
	public void setFechaPagado(Date fechaPagado) {
		this.fechaPagado = fechaPagado;
	}
	public String getFechaPagadoStr() {
		if(this.getFechaPagado()!=null)
			fechaPagadoStr = FechaUtil.ConvertirFechaDDMMYYYY(this.fechaPagado);
		return fechaPagadoStr;
	}
	public void setFechaPagadoStr(String fechaPagadoStr) {
		this.fechaPagadoStr = fechaPagadoStr;
	}
	public String getEstadoRendicion() {
		return estadoRendicion;
	}
	public void setEstadoRendicion(String estadoRendicion) {
		this.estadoRendicion = estadoRendicion;
	}
	public String getEstadoRendicionDescripcion() {
		if(this.getEstadoRendicion()!=null) {
			switch (this.getEstadoRendicion()) {
			case "1":
				estadoRendicionDescripcion="REGISTRADO";
				break;
			case "2":
				estadoRendicionDescripcion="ENVIADO";
				break;
			case "3":
				estadoRendicionDescripcion="OBSERVADO";
				break;
			case "4":
				estadoRendicionDescripcion="APROBADO SUB JEFE";
				break;
			case "5":
				estadoRendicionDescripcion="APROBADO JEFE";
				break;
			case "9":
				estadoRendicionDescripcion="APROBADO TESORERIA";
				break;
			}
		}
		return estadoRendicionDescripcion;
	}
	public void setEstadoRendicionDescripcion(String estadoRendicionDescripcion) {
		this.estadoRendicionDescripcion = estadoRendicionDescripcion;
	}
	public String getObservacionRendicion() {
		return observacionRendicion;
	}
	public void setObservacionRendicion(String observacionRendicion) {
		this.observacionRendicion = observacionRendicion;
	}
	public boolean isObservado() {
		return observado;
	}
	public void setObservado(boolean observado) {
		this.observado = observado;
	}
	public boolean isMarcado() {
		return marcado;
	}
	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	
	
}
