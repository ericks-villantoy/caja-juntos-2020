package pe.gob.juntos.request;

public class TarifarioConsultaRequest {

	private String idSede;
	private String nombreRuta;
	private boolean activo;
	
	public String getIdSede() {
		return idSede;
	}
	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}
	public String getNombreRuta() {
		return nombreRuta;
	}
	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "TarifarioConsultaRequest [idSede=" + idSede + ", nombreRuta=" + nombreRuta + ", activo=" + activo + "]";
	}
	
		
	
}
