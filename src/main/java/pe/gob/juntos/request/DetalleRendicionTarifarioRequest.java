package pe.gob.juntos.request;

public class DetalleRendicionTarifarioRequest {

	private Long idRutaRendicionDetalle;
	private Long idDetalleRendicion;
	private Long idRendicion;
	private Long idRutaSede;
	private DetalleRendicionRequest detalleRendicionReq;
	private RendicionAdministradoRequest rendicionReq;
	private boolean eliminar;
	
	public Long getIdRutaRendicionDetalle() {
		return idRutaRendicionDetalle;
	}
	public void setIdRutaRendicionDetalle(Long idRutaRendicionDetalle) {
		this.idRutaRendicionDetalle = idRutaRendicionDetalle;
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
	public boolean isEliminar() {
		return eliminar;
	}
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
	public Long getIdRutaSede() {
		return idRutaSede;
	}
	public void setIdRutaSede(Long idRutaSede) {
		this.idRutaSede = idRutaSede;
	}
	public DetalleRendicionRequest getDetalleRendicionReq() {
		return detalleRendicionReq;
	}
	public void setDetalleRendicionReq(DetalleRendicionRequest detalleRendicionReq) {
		this.detalleRendicionReq = detalleRendicionReq;
	}
	public RendicionAdministradoRequest getRendicionReq() {
		return rendicionReq;
	}
	public void setRendicionReq(RendicionAdministradoRequest rendicionReq) {
		this.rendicionReq = rendicionReq;
	}
	
	
	
	
}
