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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CC_TR_DETRENDICAJACHICA")
@Where(clause = "STR_ESBORRADO='0'")
@JsonIgnoreProperties({"listadoRutaDetalleRendicion"})
public class DetalleRendicionCajaChica {
	
	@Id
	@Column(name="INT_IDDETRENDICAJACHICA")
	@SequenceGenerator(name="SEQ_DET_RENDICIONCC",sequenceName="SEQ_DETALLE_RENDICION_CCH", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DET_RENDICIONCC")
	private Long idDetalleRendicionCajachica;
	
	@ManyToOne
	@JoinColumn(name="INT_IDRENDICAJACHICA")
	private RendicionCajaChica rendicionCajaChica;
	
	@ManyToOne
	@JoinColumn(name="INT_IDDETREND")
	private DetalleRendicion detalleRendicion;
	
	@Column(name="STR_ESTADO")
	private String estado;
	
	@Column(name="STR_COMENTARIO")
	private String comentario;	
	
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
	private String vigente = "1";
	
	@Column(name="STR_ESBORRADO")
	private String eliminado = "0";

	public Long getIdDetalleRendicionCajachica() {
		return idDetalleRendicionCajachica;
	}

	public void setIdDetalleRendicionCajachica(Long idDetalleRendicionCajachica) {
		this.idDetalleRendicionCajachica = idDetalleRendicionCajachica;
	}

	public RendicionCajaChica getRendicionCajaChica() {
		return rendicionCajaChica;
	}

	public void setRendicionCajaChica(RendicionCajaChica rendicionCajaChica) {
		this.rendicionCajaChica = rendicionCajaChica;
	}

	public DetalleRendicion getDetalleRendicion() {
		return detalleRendicion;
	}

	public void setDetalleRendicion(DetalleRendicion detalleRendicion) {
		this.detalleRendicion = detalleRendicion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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
