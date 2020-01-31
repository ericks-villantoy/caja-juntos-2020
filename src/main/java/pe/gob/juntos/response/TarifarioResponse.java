package pe.gob.juntos.response;

import pe.gob.juntos.entity.Sede;

public class TarifarioResponse {

	private Long idRutaSede;
	private Sede sede;
	private String codigoRuta;
	private String ubigeoPartida;
	private String descripcionUbigeoPartida;
	private String ubigeoLlegada;
	private String descripcionUbigeoLlegada;
	private int tarifa;
	private int tiempo;
	private String vigente;
	
	public Long getIdRutaSede() {
		return idRutaSede;
	}
	public void setIdRutaSede(Long idRutaSede) {
		this.idRutaSede = idRutaSede;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
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
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	@Override
	public String toString() {
		return "RutaSedeRequest [idRutaSede=" + idRutaSede + ", sede=" + sede + ", codigoRuta=" + codigoRuta
				+ ", ubigeoPartida=" + ubigeoPartida + ", descripcionUbigeoPartida=" + descripcionUbigeoPartida
				+ ", ubigeoLlegada=" + ubigeoLlegada + ", descripcionUbigeoLlegada=" + descripcionUbigeoLlegada
				+ ", tarifa=" + tarifa + ", tiempo=" + tiempo + ", vigente=" + vigente + "]";
	}
	
	
	
}
