package pe.gob.juntos.response;

import java.util.Date;
import java.util.List;

import pe.gob.juntos.entity.Administrado;
import pe.gob.juntos.entity.RendicionCajaChica;

public class RendicionAdministradoResponse {

	private Long idRendicion;
	private Administrado administrado;
	private RendicionCajaChica rendicionCajachica;
	private Date fechaInicio;
	private float importeTotal;
	private String documentoReferencia;
	private String comentario;
	private String estado;
	private String estadoDescripcion;
	private Date fechaRendido;
	private Date fechaAprobado;
	private String pagado;
	private Date fechaPagado;
	private String usuarioRegistraPagado;
	private String usuarioRegistraAprobado;
	private String fechaInicioTxt;
	private List<DetalleRendicionResponse> listadoDetallesRendicion;
	private boolean puedeEditar;
	private boolean puedeEnviar;
	private boolean puedeAprobar;
	
	public Long getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(Long idRendicion) {
		this.idRendicion = idRendicion;
	}
	public Administrado getAdministrado() {
		return administrado;
	}
	public void setAdministrado(Administrado administrado) {
		this.administrado = administrado;
	}
	public RendicionCajaChica getRendicionCajachica() {
		return rendicionCajachica;
	}
	public void setRendicionCajachica(RendicionCajaChica rendicionCajachica) {
		this.rendicionCajachica = rendicionCajachica;
	}
	public Date getFechaInicio() {
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
		return "RendicionCajaChicaAdministradoRequest [idRendicion=" + idRendicion + ", administrado=" + administrado
				+ ", rendicionCajachica=" + rendicionCajachica + ", fechaInicio=" + fechaInicio + ", importeTotal="
				+ importeTotal + ", documentoReferencia=" + documentoReferencia + ", comentario=" + comentario
				+ ", estado=" + estado + ", fechaRendido=" + fechaRendido + ", fechaAprobado=" + fechaAprobado
				+ ", pagado=" + pagado + ", fechaPagado=" + fechaPagado + ", usuarioRegistraPagado="
				+ usuarioRegistraPagado + ", usuarioRegistraAprobado=" + usuarioRegistraAprobado + "]";
	}
	public String getFechaInicioTxt() {
		return fechaInicioTxt;
	}
	public void setFechaInicioTxt(String fechaInicioTxt) {
		this.fechaInicioTxt = fechaInicioTxt;
	}
	public List<DetalleRendicionResponse> getListadoDetallesRendicion() {
		return listadoDetallesRendicion;
	}
	public void setListadoDetallesRendicion(List<DetalleRendicionResponse> listadoDetallesRendicion) {
		this.listadoDetallesRendicion = listadoDetallesRendicion;
	}
	public boolean isPuedeEnviar() {
		return puedeEnviar;
	}
	public void setPuedeEnviar(boolean puedeEnviar) {
		this.puedeEnviar = puedeEnviar;
	}
	public boolean isPuedeAprobar() {
		return puedeAprobar;
	}
	public void setPuedeAprobar(boolean puedeAprobar) {
		this.puedeAprobar = puedeAprobar;
	}
	public String getEstadoDescripcion() {
		
		if(this.getEstado()!=null) {
			switch (this.getEstado()) {
			case "1":
				estadoDescripcion="REGISTRADO";
				break;
			case "2":
				estadoDescripcion="ENVIADO";
				break;
			case "3":
				estadoDescripcion="OBSERVADO";
				break;
			case "4":
				estadoDescripcion="APROBADO SUB JEFE";
				break;
			case "5":
				estadoDescripcion="APROBADO JEFE";
				break;
			case "9":
				estadoDescripcion="APROBADO TESORERIA";
				break;
			}
		}
		return estadoDescripcion;
	}
	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
	}
	public boolean isPuedeEditar() {
		puedeEditar=false;
		if(this.getEstado()!=null) {
			switch (this.getEstado()) {
			case "1":
				puedeEditar=true;
				break;
			case "3":
				puedeEditar=true;
				break;
			}
		}
		return puedeEditar;
	}
	public void setPuedeEditar(boolean puedeEditar) {
		this.puedeEditar = puedeEditar;
	}
	
	
}
