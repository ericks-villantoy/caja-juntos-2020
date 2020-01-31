package pe.gob.juntos.response;

import pe.gob.juntos.entity.DetalleRendicion;
import pe.gob.juntos.entity.RendicionCajaChica;

public class DetalleRendicionCajaChicaResponse {
	
	private Long idDetalleRendicionCajachica;
	private RendicionCajaChica rendicionCajaChica;
	private DetalleRendicion detalleRendicion;
	private String estado;
	private String comentario;
	
	public Long getIdDetalleRendicionCajachica() {
		return idDetalleRendicionCajachica;
	}
	public void setIdDetalleRendicionCajachica(Long idDetalleRendicionCajachica) {
		this.idDetalleRendicionCajachica = idDetalleRendicionCajachica;
	}
	public RendicionCajaChica getRendicionCajaChica() {
		return rendicionCajaChica;
	}
	public void setRendicionCajaChica(RendicionCajaChica rendicionCajaChica) {
		this.rendicionCajaChica = rendicionCajaChica;
	}
	public DetalleRendicion getDetalleRendicion() {
		return detalleRendicion;
	}
	public void setDetalleRendicion(DetalleRendicion detalleRendicion) {
		this.detalleRendicion = detalleRendicion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}	
	
}
