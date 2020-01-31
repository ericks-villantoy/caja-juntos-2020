package pe.gob.juntos.request;

import java.util.Date;

import pe.gob.juntos.util.FechaUtil;

public class RendicionConsultaAdministradoRequest {

	private String idSede;
	private Date fechaInicio;
	private Date fechaFin;
	private String fechaInicioTxt;
	private String fechaFinTxt;
	private String idRendicionCajaChica;
	
	
	public String getIdSede() {
		return idSede;
	}
	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}
	public Date getFechaInicio() {
		if(this.getFechaInicioTxt() != null && this.getFechaInicioTxt().length()>0) {
			fechaInicio= FechaUtil.ConvertirCadenaFechaDDMMYYYY(fechaInicioTxt);
		}
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		if(this.getFechaFinTxt() != null && this.getFechaFinTxt().length()>0) {
			fechaFin= FechaUtil.ConvertirCadenaFechaDDMMYYYY(fechaFinTxt);
		}
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public String toString() {
		return "CajaChicaRequest [idSede=" + idSede + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	public String getFechaInicioTxt() {
		return fechaInicioTxt;
	}
	public void setFechaInicioTxt(String fechaInicioTxt) {
		this.fechaInicioTxt = fechaInicioTxt;
	}
	public String getFechaFinTxt() {
		return fechaFinTxt;
	}
	public void setFechaFinTxt(String fechaFinTxt) {
		this.fechaFinTxt = fechaFinTxt;
	}
	public String getIdRendicionCajaChica() {
		return idRendicionCajaChica;
	}
	public void setIdRendicionCajaChica(String idRendicionCajaChica) {
		this.idRendicionCajaChica = idRendicionCajaChica;
	}
	
	
		
}
