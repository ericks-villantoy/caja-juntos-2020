package pe.gob.juntos.response;

import pe.gob.juntos.entity.Sede;

public class ControlCierreMesCajaChicaResponse {

	private Long idControlCierreMes;
	private Sede sede;
	private Long anio;
	private Long mes;
	private String estado;
	private String anotacion;
	private String usuarioCreacion;
	private String fechaCreacion;
	private String historial;
	public Long getIdControlCierreMes() {
		return idControlCierreMes;
	}

	public void setIdControlCierreMes(Long idControlCierreMes) {
		this.idControlCierreMes = idControlCierreMes;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAnotacion() {
		return anotacion;
	}

	public void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getHistorial() {
		return historial;
	}

	public void setHistorial(String historial) {
		this.historial = historial;
	}

	
	
}
