package pe.gob.juntos.request;

public class RendicionAprobacionAdministradoRequest {

	private Long idRendicion;
	private String observacion;
	private int accion;

	public Long getIdRendicion() {
		return idRendicion;
	}

	public void setIdRendicion(Long idRendicion) {
		this.idRendicion = idRendicion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getAccion() {
		return accion;
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}
	
	
	
		
}
