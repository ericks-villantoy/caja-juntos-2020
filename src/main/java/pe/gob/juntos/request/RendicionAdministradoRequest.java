package pe.gob.juntos.request;

import java.util.Date;
import java.util.List;

import pe.gob.juntos.entity.RendicionCajaChica;
import pe.gob.juntos.util.FechaUtil;

public class RendicionAdministradoRequest {

	private Long idRendicion;
	private String idAdministrado;
	private RendicionCajaChica rendicionCajachica;
	private Date fechaInicio;
	private String fechaInicioTxt;
	private float importeTotal;
	private String documentoReferencia;
	private String comentario;
	private String estado;
	private Date fechaRendido;
	private Date fechaAprobado;
	private String pagado;
	private Date fechaPagado;
	private String usuarioRegistraPagado;
	private String usuarioRegistraAprobado;
	private List<DetalleRendicionRequest> listadoDetallesRendicion;
	private boolean eliminar;
	private boolean enviarAprobar;
	private List<String> listadoDocumentoArchivo;
	
	public Long getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(Long idRendicion) {
		this.idRendicion = idRendicion;
	}
	
	public RendicionCajaChica getRendicionCajachica() {
		return rendicionCajachica;
	}
	public void setRendicionCajachica(RendicionCajaChica rendicionCajachica) {
		this.rendicionCajachica = rendicionCajachica;
	}
	public Date getFechaInicio() {
		if(this.getFechaInicioTxt() != null && this.getFechaInicioTxt().length()>0) {
			fechaInicio= FechaUtil.ConvertirCadenaFechaDDMMYYYY(fechaInicioTxt);
		}
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public float getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getDocumentoReferencia() {
		return documentoReferencia;
	}
	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaRendido() {
		return fechaRendido;
	}
	public void setFechaRendido(Date fechaRendido) {
		this.fechaRendido = fechaRendido;
	}
	public Date getFechaAprobado() {
		return fechaAprobado;
	}
	public void setFechaAprobado(Date fechaAprobado) {
		this.fechaAprobado = fechaAprobado;
	}
	public String getPagado() {
		return pagado;
	}
	public void setPagado(String pagado) {
		this.pagado = pagado;
	}
	public Date getFechaPagado() {
		return fechaPagado;
	}
	public void setFechaPagado(Date fechaPagado) {
		this.fechaPagado = fechaPagado;
	}
	public String getUsuarioRegistraPagado() {
		return usuarioRegistraPagado;
	}
	public void setUsuarioRegistraPagado(String usuarioRegistraPagado) {
		this.usuarioRegistraPagado = usuarioRegistraPagado;
	}
	public String getUsuarioRegistraAprobado() {
		return usuarioRegistraAprobado;
	}
	public void setUsuarioRegistraAprobado(String usuarioRegistraAprobado) {
		this.usuarioRegistraAprobado = usuarioRegistraAprobado;
	}
	@Override
	public String toString() {
		return "RendicionCajaChicaAdministradoRequest [idRendicion=" + idRendicion + ", idAdministrado=" + idAdministrado
				+ ", rendicionCajachica=" + rendicionCajachica + ", fechaInicio=" + fechaInicio + ", importeTotal="
				+ importeTotal + ", documentoReferencia=" + documentoReferencia + ", comentario=" + comentario
				+ ", estado=" + estado + ", fechaRendido=" + fechaRendido + ", fechaAprobado=" + fechaAprobado
				+ ", pagado=" + pagado + ", fechaPagado=" + fechaPagado + ", usuarioRegistraPagado="
				+ usuarioRegistraPagado + ", usuarioRegistraAprobado=" + usuarioRegistraAprobado + "]";
	}
	public String getIdAdministrado() {
		return idAdministrado;
	}
	public void setIdAdministrado(String idAdministrado) {
		this.idAdministrado = idAdministrado;
	}
	public List<DetalleRendicionRequest> getListadoDetallesRendicion() {
		return listadoDetallesRendicion;
	}
	public void setListadoDetallesRendicion(List<DetalleRendicionRequest> listadoDetallesRendicion) {
		this.listadoDetallesRendicion = listadoDetallesRendicion;
	}
	public boolean isEliminar() {
		return eliminar;
	}
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
	public String getFechaInicioTxt() {
		return fechaInicioTxt;
	}
	public void setFechaInicioTxt(String fechaInicioTxt) {
		this.fechaInicioTxt = fechaInicioTxt;
	}
	public List<String> getListadoDocumentoArchivo() {
		return listadoDocumentoArchivo;
	}
	public void setListadoDocumentoArchivo(List<String> listadoDocumentoArchivo) {
		this.listadoDocumentoArchivo = listadoDocumentoArchivo;
	}
	public boolean isEnviarAprobar() {
		return enviarAprobar;
	}
	public void setEnviarAprobar(boolean enviarAprobar) {
		this.enviarAprobar = enviarAprobar;
	}
	
	
}
