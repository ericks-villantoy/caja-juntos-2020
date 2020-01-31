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

@Entity
@Table(name = "CC_TR_FLUJOAPROBACION")
public class FlujoAprobacion {

	@Id
	@Column(name="INT_IDFLUJOAPROBACION")
	@SequenceGenerator(name="SEQ_FLU_APRO",sequenceName="SEQ_FLUJOAPROBACION", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_FLU_APRO")
	private Long idFlujoAprobacion;
	
	@ManyToOne
	@JoinColumn(name="INT_IDRENDICION")
	private Rendicion rendicion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_FECHA")
	private Date fechaAprobacion;
	
	@Column(name="STR_USUARIO")
	private String usuarioAprobacion;
	
	@Column(name="STR_ESTADOINICIO")
	private String estadoInicio;
	
	@Column(name="STR_ESTADOFIN")
	private String estadoFin;
	
	@Column(name="STR_COMENTARIO")
	private String comentario;

	public Long getIdFlujoAprobacion() {
		return idFlujoAprobacion;
	}

	public void setIdFlujoAprobacion(Long idFlujoAprobacion) {
		this.idFlujoAprobacion = idFlujoAprobacion;
	}

	public Rendicion getRendicion() {
		return rendicion;
	}

	public void setRendicion(Rendicion rendicion) {
		this.rendicion = rendicion;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getUsuarioAprobacion() {
		return usuarioAprobacion;
	}

	public void setUsuarioAprobacion(String usuarioAprobacion) {
		this.usuarioAprobacion = usuarioAprobacion;
	}

	public String getEstadoInicio() {
		return estadoInicio;
	}

	public void setEstadoInicio(String estadoInicio) {
		this.estadoInicio = estadoInicio;
	}

	public String getEstadoFin() {
		return estadoFin;
	}

	public void setEstadoFin(String estadoFin) {
		this.estadoFin = estadoFin;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
