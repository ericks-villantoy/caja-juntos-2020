package pe.gob.juntos.response;

public class RespuestaGeneralResponse {
	
	private boolean observado;
	private boolean exito;
	private String  respuesta;
	private Long idRendicion;
	private Long idDetalleRendicion;
	
	
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public boolean isExito() {
		return exito;
	}
	public void setExito(boolean exito) {
		this.exito = exito;
	}
	public boolean isObservado() {
		return observado;
	}
	public void setObservado(boolean observado) {
		this.observado = observado;
	}
	public Long getIdDetalleRendicion() {
		return idDetalleRendicion;
	}
	public void setIdDetalleRendicion(Long idDetalleRendicion) {
		this.idDetalleRendicion = idDetalleRendicion;
	}
	public Long getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(Long idRendicion) {
		this.idRendicion = idRendicion;
	}
	
}
