package pe.gob.juntos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CC_MA_DATOSCOMISIONADO")
public class VistaDatosComisionado {

	@Id
	@Column(name="NRODNI")
	private String numeroDocumento;
	@Column(name="X_APPATERNO")
	private String apellidoPaterno;
	@Column(name="X_APMATERNO")
	private String apellidoMaterno;
	@Column(name="X_NOMBRES")
	private String nombres;
	@Column(name="X_USER")
	private String usuario;
	@Column(name="X_EMAIL")
	private String email;
	@Column(name="DOMICILIO")
	private String domicilio;
	@Column(name="SEC_UNIDAD")
	private Long codigoUnidad;
	@Column(name="DESC_UNIDAD")
	private String descripcionUnidad;
	@Column(name="SEC_CARGO")
	private Long cargo;
	@Column(name="DESC_CARGO")
	private String descripcionCargo;
	@Column(name="C_REGION")
	private String codigoRegion;
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public Long getCodigoUnidad() {
		return codigoUnidad;
	}
	public void setCodigoUnidad(Long codigoUnidad) {
		this.codigoUnidad = codigoUnidad;
	}
	public String getDescripcionUnidad() {
		return descripcionUnidad;
	}
	public void setDescripcionUnidad(String descripcionUnidad) {
		this.descripcionUnidad = descripcionUnidad;
	}
	public Long getCargo() {
		return cargo;
	}
	public void setCargo(Long cargo) {
		this.cargo = cargo;
	}
	public String getDescripcionCargo() {
		return descripcionCargo;
	}
	public void setDescripcionCargo(String descripcionCargo) {
		this.descripcionCargo = descripcionCargo;
	}
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	
}
