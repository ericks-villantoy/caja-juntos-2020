package pe.gob.juntos.response;

public class VisualizaArchivoResponse {
	
	private String archivoBase64;
	private boolean archivoCreado;
	private String rutaCreado;
	
	
	public String getArchivoBase64() {
		return archivoBase64;
	}

	public void setArchivoBase64(String archivoBase64) {
		this.archivoBase64 = archivoBase64;
	}

	public boolean isArchivoCreado() {
		return archivoCreado;
	}

	public void setArchivoCreado(boolean archivoCreado) {
		this.archivoCreado = archivoCreado;
	}

	public String getRutaCreado() {
		return rutaCreado;
	}

	public void setRutaCreado(String rutaCreado) {
		this.rutaCreado = rutaCreado;
	}
	
}
