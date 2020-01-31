package pe.gob.juntos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "CC_MA_ADMINISTRADO")
@Where(clause = "STR_ESBORRADO='0'")
public class Administrado {

	@Id
	@Column(name="COD_USUARIO")
	private String idAdministrado;
	
	@ManyToOne
	@JoinColumn(name="C_REGION")
	private Sede sede;
	
	@Column(name="STR_TIPODOCUMENTO")
	private String tipoDocumento;
	
	@Column(name="STR_NUMERODOCUMENTO")
	private String numeroDocumento;
	
	@Column(name="STR_NOMBRE")
	private String nombre;
	
	@Column(name="STR_APEPATERNO")
	private String apellidoPaterno;
	
	@Column(name="STR_APEMATERNO")
	private String apellidoMaterno;
	
	@Column(name="STR_USUCREACION")
	private String usuarioCreacion;
	@Column(name="DATE_FECHACREACION")
	private Date fechaCreacion;
	@Column(name="STR_USUMODIFICACION")
	private String usuarioModificacion;
	@Column(name="DATE_FECHAMODIFICACION")
	private Date fechaModificacion;
	@Column(name="STR_ESVIGENTE")
	private String vigente;
	@Column(name="STR_ESBORRADO")
	private String eliminado;
	
	@Transient
	private String nombresCompletos;
	
	public String getIdAdministrado() {
		return idAdministrado;
	}
	public void setIdAdministrado(String idAdministrado) {
		this.idAdministrado = idAdministrado;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public String getEliminado() {
		return eliminado;
	}
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	public String getNombresCompletos() {
		
			if(this.getApellidoPaterno() != null && this.getApellidoMaterno() != null && this.getNombre() != null ) {
				nombresCompletos = this.getApellidoPaterno()+' ' + this.getApellidoMaterno()+ ' ' + this.getNombre();
			}else {
				nombresCompletos = "";
			}
		return nombresCompletos;
	}
	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}
	
	
}
