package pe.gob.juntos.request;

public class VisualizaArchivoRequest {

	private Long idRendicion;
	private Long idDetalleRendicion;
	private String nombreArchivo;
	
	public Long getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(Long idRendicion) {
		this.idRendicion = idRendicion;
	}
	public Long getIdDetalleRendicion() {
		return idDetalleRendicion;
	}
	public void setIdDetalleRendicion(Long idDetalleRendicion) {
		this.idDetalleRendicion = idDetalleRendicion;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
		
}
