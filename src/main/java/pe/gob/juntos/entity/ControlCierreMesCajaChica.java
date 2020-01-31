package pe.gob.juntos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "CC_TR_CONTROLCIERREMES")
@Where(clause = "STR_ESBORRADO='0'")
public class ControlCierreMesCajaChica {

	@Id
	@Column(name="INT_IDCONTROLCIERREMES")
	@SequenceGenerator(name="SEQ_",sequenceName="SEQ_REAPERTURA_CIERRE_CC", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_")
	private Long idControlCierreMes;
	
	@ManyToOne
	@JoinColumn(name="C_REGION")
	private Sede sede;
	
	@Column(name="INT_ANIO")
	private Long anio;
	
	@Column(name="INT_MES")
	private Long mes;
	
	@Column(name="STR_ESTADO")
	private String estado;
	
	@Column(name="STR_ANOTACION")
	private String anotacion;
	
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
	private String vigente="1";
	
	@Column(name="STR_ESBORRADO")
	private String eliminado="0";

	public Long getIdControlCierreMes() {
		return idControlCierreMes;
	}

	public void setIdControlCierreMes(Long idControlCierreMes) {
		this.idControlCierreMes = idControlCierreMes;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAnotacion() {
		return anotacion;
	}

	public void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
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
