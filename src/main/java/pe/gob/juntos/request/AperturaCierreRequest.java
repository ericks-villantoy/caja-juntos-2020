package pe.gob.juntos.request;

import pe.gob.juntos.entity.Especifica;
import pe.gob.juntos.entity.Sede;

public class AperturaCierreRequest {
	
	private Long idAperturaCierre;
	private Sede sede;
	private Especifica especifica;
	private Long anio;
	private float montoApertura;
	private float montoLiquidacion;
	private float montoEjecucion;
	private float saldo;
	private String flagCerrado;
	private String descCerrado;
	private String codRegion;
	
	private Long idEspecifica;
	private String idSede;
	
	
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
	public float getMontoEjecucion() {
		return montoEjecucion;
	}
	public void setMontoEjecucion(float montoEjecucion) {
		this.montoEjecucion = montoEjecucion;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
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
	public String getCodRegion() {
		return codRegion;
	}
	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}
	
	
	
	public Long getIdEspecifica() {
		return idEspecifica;
	}
	public void setIdEspecifica(Long idEspecifica) {
		this.idEspecifica = idEspecifica;
	}
	public String getIdSede() {
		return idSede;
	}
	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}
	@Override
	public String toString() {
		return "AperturaCierreRequest [idAperturaCierre=" + idAperturaCierre + ", sede=" + sede
				+ ", especifica=" + especifica + ", anio=" + anio + ", montoApertura="
				+ montoApertura + ", montoLiquidacion=" + montoLiquidacion + ", montoEjecucion=" + montoEjecucion
				+ ", saldo=" + saldo + ", flagCerrado=" + flagCerrado  + "]";
	}
	
}
