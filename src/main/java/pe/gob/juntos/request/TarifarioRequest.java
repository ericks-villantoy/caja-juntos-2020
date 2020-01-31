package pe.gob.juntos.request;

public class TarifarioRequest {

	private Long idRutaSede;
	private String idSede;
	private String codigoRuta;
	private String ubigeoPartida;
	private String descripcionUbigeoPartida;
	private String ubigeoLlegada;
	private String descripcionUbigeoLlegada;
	private int tarifa;
	private int tiempo;
	private boolean eliminar;
	
	
	public Long getIdRutaSede() {
		return idRutaSede;
	}


	public void setIdRutaSede(Long idRutaSede) {
		this.idRutaSede = idRutaSede;
	}


	public String getIdSede() {
		return idSede;
	}


	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}


	public String getCodigoRuta() {
		return codigoRuta;
	}


	public void setCodigoRuta(String codigoRuta) {
		this.codigoRuta = codigoRuta;
	}


	public String getUbigeoPartida() {
		return ubigeoPartida;
	}


	public void setUbigeoPartida(String ubigeoPartida) {
		this.ubigeoPartida = ubigeoPartida;
	}


	public String getDescripcionUbigeoPartida() {
		return descripcionUbigeoPartida;
	}


	public void setDescripcionUbigeoPartida(String descripcionUbigeoPartida) {
		this.descripcionUbigeoPartida = descripcionUbigeoPartida;
	}


	public String getUbigeoLlegada() {
		return ubigeoLlegada;
	}


	public void setUbigeoLlegada(String ubigeoLlegada) {
		this.ubigeoLlegada = ubigeoLlegada;
	}


	public String getDescripcionUbigeoLlegada() {
		return descripcionUbigeoLlegada;
	}


	public void setDescripcionUbigeoLlegada(String descripcionUbigeoLlegada) {
		this.descripcionUbigeoLlegada = descripcionUbigeoLlegada;
	}


	public int getTarifa() {
		return tarifa;
	}


	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}


	public int getTiempo() {
		return tiempo;
	}


	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}


	@Override
	public String toString() {
		return "TarifarioRequest [idRutaSede=" + idRutaSede + ", idSede=" + idSede + ", codigoRuta=" + codigoRuta
				+ ", ubigeoPartida=" + ubigeoPartida + ", descripcionUbigeoPartida=" + descripcionUbigeoPartida
				+ ", ubigeoLlegada=" + ubigeoLlegada + ", descripcionUbigeoLlegada=" + descripcionUbigeoLlegada
				+ ", tarifa=" + tarifa + ", tiempo=" + tiempo + "]";
	}


	public boolean isEliminar() {
		return eliminar;
	}


	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
		
}
