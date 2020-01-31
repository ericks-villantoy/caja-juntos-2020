package pe.gob.juntos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "CC_MA_SEDE")
@Where(clause = "STR_ESBORRADO='0'")
public class Sede {

	@Id
	@Column(name="C_REGION")
	private String idSede;
	@Column(name="STR_CODSEDE")
	private String codigoSede;
	@Column(name="STR_DESCRIPCIONSEDE")
	private String descripcionSede;
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
		
	public String getIdSede() {
		return idSede;
	}
	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}
	public String getCodigoSede() {
		return codigoSede;
	}
	public void setCodigoSede(String codigoSede) {
		this.codigoSede = codigoSede;
	}
	public String getDescripcionSede() {
		return descripcionSede;
	}
	public void setDescripcionSede(String descripcionSede) {
		this.descripcionSede = descripcionSede;
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
	
	
}
