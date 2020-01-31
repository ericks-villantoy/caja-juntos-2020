package pe.gob.juntos.request;

import java.util.Date;
public class RutaDetalleRendicionRequest {

	private Long idRutaRendicionDetalle;
	private Long idRutaSede;
	private Long idDetalleRendicion;
	private Long importe;
	private Date fecha;
	
	public Long getIdRutaRendicionDetalle() {
		return idRutaRendicionDetalle;
	}
	public void setIdRutaRendicionDetalle(Long idRutaRendicionDetalle) {
		this.idRutaRendicionDetalle = idRutaRendicionDetalle;
	}
	public Long getIdRutaSede() {
		return idRutaSede;
	}
	public void setIdRutaSede(Long idRutaSede) {
		this.idRutaSede = idRutaSede;
	}
	public Long getIdDetalleRendicion() {
		return idDetalleRendicion;
	}
	public void setIdDetalleRendicion(Long idDetalleRendicion) {
		this.idDetalleRendicion = idDetalleRendicion;
	}
	public Long getImporte() {
		return importe;
	}
	public void setImporte(Long importe) {
		this.importe = importe;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
}
