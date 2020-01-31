package pe.gob.juntos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import pe.gob.juntos.util.FechaUtil;

@Entity
@Table(name = "CC_MA_ESPECIFICA")
@Where(clause = "STR_ESBORRADO='0'")
public class Especifica {

	@Id
	@Column(name="INT_IDESPECIFICA")
	private Long idEspecifica;
	
	@ManyToOne
	@JoinColumn(name="C_REGION")
	private Sede sede;
	
	@Column(name="STR_CODIGOESPECIFICA")
	private String codigoCalse;
	
	@Column(name="STR_DETALLEESPECIFICA")
	private String descripcionEspecifica;
	
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
		
	@Transient
	private String codigoDescEspecifica;
	
	
	public String getCodigoDescEspecifica() {
		if(this.getCodigoCalse() != null ) {
			codigoDescEspecifica= this.getCodigoCalse() + " - " +this.getDescripcionEspecifica();
		}
		
		return codigoDescEspecifica;
	}
	
	public Long getIdEspecifica() {
		return idEspecifica;
	}
	public void setIdEspecifica(Long idEspecifica) {
		this.idEspecifica = idEspecifica;
	}
	public String getCodigoCalse() {
		return codigoCalse;
	}
	public void setCodigoCalse(String codigoCalse) {
		this.codigoCalse = codigoCalse;
	}
	public String getDescripcionEspecifica() {
		return descripcionEspecifica;
	}
	public void setDescripcionEspecifica(String descripcionEspecifica) {
		this.descripcionEspecifica = descripcionEspecifica;
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
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	
	
	
}
