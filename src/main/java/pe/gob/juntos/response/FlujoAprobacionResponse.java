package pe.gob.juntos.response;

public class FlujoAprobacionResponse {
	
	private Long idRendicion;
	private String  fechaAprobacion;
	private String usuarioAprobacion;
	private String estadoAprobacion;
	private String  perfil;
	private String  observacion;
	private String descripcion;
	
	public String getFechaAprobacion() {
		return fechaAprobacion;
	}
	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	public String getUsuarioAprobacion() {
		return usuarioAprobacion;
	}
	public void setUsuarioAprobacion(String usuarioAprobacion) {
		this.usuarioAprobacion = usuarioAprobacion;
	}
	public String getEstadoAprobacion() {
		return estadoAprobacion;
	}
	public void setEstadoAprobacion(String estadoAprobacion) {
		this.estadoAprobacion = estadoAprobacion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Long getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(Long idRendicion) {
		this.idRendicion = idRendicion;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getDescripcion() {
		descripcion = this.fechaAprobacion+" " + this.estadoAprobacion+" "+this.usuarioAprobacion+" " + this.perfil;
		return descripcion;
	}
	
}
