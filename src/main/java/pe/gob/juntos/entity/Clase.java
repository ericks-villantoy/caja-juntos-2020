package pe.gob.juntos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "CC_MA_CLASE")
@Where(clause = "STR_ESBORRADO='0'")
public class Clase {

	@Id
	@Column(name="INT_IDCLASE")
	private Long idClase;
	
	@Column(name="STR_CODCLASE")
	private String codigoClase;
	
	@Column(name="STR_DESCRIPCIONCLASE")
	private String descripcionClase;
	
	@Column(name="STR_USUCREACION")
	private String usuarioCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_FECHACREACION")
	private Date fechaCreacion;
	
	@Column(name="STR_USUMODIFICACION")
	private String usuarioModificacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_FECHAMODIFICACION")
	private Date fechaModificacion;
	
	@Column(name="STR_ESVIGENTE")
	private String vigente;
	
	@Column(name="STR_ESBORRADO")
	private String eliminado;
	
	public Long getIdClase() {
		return idClase;
	}
	public void setIdClase(Long idClase) {
		this.idClase = idClase;
	}
	
	public String getDescripcionClase() {
		return descripcionClase;
	}
	public void setDescripcionClase(String descripcionClase) {
		this.descripcionClase = descripcionClase;
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
	public String getCodigoClase() {
		return codigoClase;
	}
	public void setCodigoClase(String codigoClase) {
		this.codigoClase = codigoClase;
	}
	
	
	
	
}
