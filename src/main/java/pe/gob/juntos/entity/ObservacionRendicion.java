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

import org.hibernate.annotations.Where;

@Entity
@Table(name = "CC_TR_OBSERVARENDICION")
@Where(clause = "STR_ESBORRADO='0'")
public class ObservacionRendicion {

	@Id
	@Column(name="INT_IDOBSRENDICION")
	private Long idObservacionRendicion;
	
	@ManyToOne
	@JoinColumn(name="INT_IDRENDICION")
	private Rendicion rendicion;
	
	@ManyToOne
	@JoinColumn(name="INT_IDRENDICAJACHICA")
	private RendicionCajaChica rendicionCajachica;
	
	@ManyToOne
	@JoinColumn(name="INT_IDFUNCIONARIO")
	private Funcionario funcionario;
	
	@Column(name="STR_TIPOBSERVACION")
	private String tipoObservacion;
	
	@Column(name="STR_COMENTARIO")
	private String comentario;
	
	@Column(name="STR_ACCION")
	private String accion;
	
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
	
	public Long getIdObservacionRendicion() {
		return idObservacionRendicion;
	}
	public void setIdObservacionRendicion(Long idObservacionRendicion) {
		this.idObservacionRendicion = idObservacionRendicion;
	}
	public Rendicion getRendicion() {
		return rendicion;
	}
	public void setRendicion(Rendicion rendicion) {
		this.rendicion = rendicion;
	}
	public RendicionCajaChica getRendicionCajachica() {
		return rendicionCajachica;
	}
	public void setRendicionCajachica(RendicionCajaChica rendicionCajachica) {
		this.rendicionCajachica = rendicionCajachica;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getTipoObservacion() {
		return tipoObservacion;
	}
	public void setTipoObservacion(String tipoObservacion) {
		this.tipoObservacion = tipoObservacion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
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
