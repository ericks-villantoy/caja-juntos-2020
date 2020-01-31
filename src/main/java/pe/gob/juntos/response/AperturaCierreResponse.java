package pe.gob.juntos.response;

import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.entity.Sede;

public class AperturaCierreResponse {

	private Long idAperturaCierre;
	private Sede sede;
	private Especifica especifica;
	private Long anio;
	private float montoApertura;
	private float montoLiquidacion;
	private Double montoEjecucion;
	private Double saldo;
	private String flagCerrado;
	private String descCerrado;
	/*private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModificacion;
	private Date fechaModificacion;
	private String vigente;
	private String eliminado;*/
	
	public Long getIdAperturaCierre() {
		return idAperturaCierre;
	}
	public void setIdAperturaCierre(Long idAperturaCierre) {
		this.idAperturaCierre = idAperturaCierre;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public Especifica getEspecifica() {
		return especifica;
	}
	public void setEspecifica(Especifica especifica) {
		this.especifica = especifica;
	}
	public Long getAnio() {
		return anio;
	}
	public void setAnio(Long anio) {
		this.anio = anio;
	}
	public float getMontoApertura() {
		return montoApertura;
	}
	public void setMontoApertura(float montoApertura) {
		this.montoApertura = montoApertura;
	}
	public float getMontoLiquidacion() {
		return montoLiquidacion;
	}
	public void setMontoLiquidacion(float montoLiquidacion) {
		this.montoLiquidacion = montoLiquidacion;
	}
	public Double getMontoEjecucion() {
		return montoEjecucion;
	}
	public void setMontoEjecucion(Double montoEjecucion) {
		this.montoEjecucion = montoEjecucion;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public String getFlagCerrado() {
		return flagCerrado;
	}
	public void setFlagCerrado(String flagCerrado) {
		this.flagCerrado = flagCerrado;
	}	
	
	public String getDescCerrado() {
		return descCerrado;
	}
	public void setDescCerrado(String descCerrado) {
		this.descCerrado = descCerrado;
	}
	@Override
	public String toString() {
		return "AperturaCierreRequest [idAperturaCierre=" + idAperturaCierre + ", sede=" + sede
				+ ", especifica=" + especifica + ", anio=" + anio + ", montoApertura="
				+ montoApertura + ", montoLiquidacion=" + montoLiquidacion + ", montoEjecucion=" + montoEjecucion
				+ ", saldo=" + saldo + ", flagCerrado=" + flagCerrado  + "]";
	}
	
}
