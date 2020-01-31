package pe.gob.juntos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "V_ACCESO_SEGURIDAD_JUNTOS")
@Where(clause = "FLG_ESTADO='A'")
public class VistaAccesoSeguridad {

	@Id
	@Column(name="COD_USUARIO")
	private String codigoUsuario;
	@Column(name="DES_PASSWORD")
	private String clave;
	@Column(name="FLG_ESTADO")
	private String estado;
	@Column(name="X_APPATERNO")
	private String apellidoPaterno;
	@Column(name="X_APMATERNO")
	private String apellidoMaterno;
	@Column(name="X_NOMBRES")
	private String nombres;
	@Column(name="X_USER")
	private String usuario;
	@Column(name="C_DEPA")
	private String codigoDepartamento;
	@Column(name="C_DEPA2")
	private String codigoDepa;
	@Column(name="NRODNI")
	private String numeroDocumento;
	@Column(name="C_REGION")
	private String codigoRegion;
	@Column(name="X_REGION")
	private String descripcionRegion;
	@Column(name="X_EMAIL")
	private String email;
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	public String getCodigoDepa() {
		return codigoDepa;
	}
	public void setCodigoDepa(String codigoDepa) {
		this.codigoDepa = codigoDepa;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
