package pe.gob.juntos.request;

public class RendicionDetalleCajaChicaRequest {
	
	private Long idDetalleRendicion;
	private Long idRendicion;
	private String observacionRendicion;
	private Long importe;
	private boolean observado;
	private boolean marcado;
	
	
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
	public Long getImporte() {
		return importe;
	}
	public void setImporte(Long importe) {
		this.importe = importe;
	}
	
	
	
	
}
