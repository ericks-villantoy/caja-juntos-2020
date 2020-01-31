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
@Table(name = "CC_TR_RUTAXRENDETALLE")
@Where(clause = "STR_ESBORRADO='0'")
public class RutaDetalleRendicion {

	@Id
	@Column(name="INT_IDRUTAXRENDETALLE")
	@SequenceGenerator(name="SEQ_RUTADETALLEREND",sequenceName="SEQ_RUTADETALLERENDICION", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RUTADETALLEREND")
	private Long idRutaRendicionDetalle;
	
	@ManyToOne
	@JoinColumn(name="INT_IDRUTAXSEDE")
	private RutaSede rutaSede;
	
	@ManyToOne
	@JoinColumn(name="INT_IDDETREND")
	private DetalleRendicion detalleRendicion;
	
	@Column(name="INT_IMPORTE")
	private Long importe;
	
	@Column(name="STR_ESTADO")
	private String estado;
	
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
	
	public Long getIdRutaRendicionDetalle() {
		return idRutaRendicionDetalle;
	}
	public void setIdRutaRendicionDetalle(Long idRutaRendicionDetalle) {
		this.idRutaRendicionDetalle = idRutaRendicionDetalle;
	}
	public Long getImporte() {
		return importe;
	}
	public void setImporte(Long importe) {
		this.importe = importe;
	}
	public RutaSede getRutaSede() {
		return rutaSede;
	}
	public void setRutaSede(RutaSede rutaSede) {
		this.rutaSede = rutaSede;
	}
	public DetalleRendicion getDetalleRendicion() {
		return detalleRendicion;
	}
	public void setDetalleRendicion(DetalleRendicion detalleRendicion) {
		this.detalleRendicion = detalleRendicion;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
