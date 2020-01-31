package pe.gob.juntos.response;

import pe.gob.juntos.entity.DetalleRendicion;
import pe.gob.juntos.entity.RutaSede;

public class RutaDetalleRendicionResponse {

	private Long idRutaRendicionDetalle;
	private RutaSede rutaSede;
	private DetalleRendicion detalleRendicion;
	private Long importe;
	
	public Long getIdRutaRendicionDetalle() {
		return idRutaRendicionDetalle;
	}
	public void setIdRutaRendicionDetalle(Long idRutaRendicionDetalle) {
		this.idRutaRendicionDetalle = idRutaRendicionDetalle;
	}
	public RutaSede getRutaSede() {
		return rutaSede;
	}
	public void setRutaSede(RutaSede rutaSede) {
		this.rutaSede = rutaSede;
	}
	public DetalleRendicion getDetalleRendicion() {
		return detalleRendicion;
	}
	public void setDetalleRendicion(DetalleRendicion detalleRendicion) {
		this.detalleRendicion = detalleRendicion;
	}
	public Long getImporte() {
		return importe;
	}
	public void setImporte(Long importe) {
		this.importe = importe;
	}
	
	
}
