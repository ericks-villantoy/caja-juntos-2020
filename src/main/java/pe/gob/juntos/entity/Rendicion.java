package pe.gob.juntos.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pe.gob.juntos.util.FechaUtil;

@Entity
@Table(name = "CC_TR_RENDICION")
@Where(clause = "STR_ESBORRADO='0'")
@JsonIgnoreProperties({"listadoDetallesRendicion"})
public class Rendicion {

	@Id
	@Column(name="INT_IDRENDICION")
	@SequenceGenerator(name="SEQ_RENDICION",sequenceName="SEQ_RENDICION", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RENDICION")
	private Long idRendicion;
	
	@ManyToOne
	@JoinColumn(name="COD_USUARIO")
	private Administrado administrado;
	
	@ManyToOne
	@JoinColumn(name="C_REGION")
	private Sede sede;
	
	@Column(name="SEC_UNIDAD")
	private Long sedeUnidad;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DTE_FECHAINICIO")
	private Date fechaInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DTE_FECHAFIN")
	private Date fechaFin;
	
	@Column(name="INT_IMPORTETOTAL")
	private float importeTotal = 0;
	
	@Column(name="STR_DOCREFERENCIA")
	private String documentoReferencia;
	
	@Column(name="STR_COMENTARIO")
	private String comentario;
	
	@Column(name="STR_ESTADO")
	private String estado="1";
	
	@Temporal(TemporalType.DATE)
	@Column(name="DTE_FECHARENDIDO")
	private Date fechaRendido;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DTE_FECHAAPROBADO")
	private Date fechaAprobado;
	
	@Column(name="STR_PAGADO")
	private String pagado;
	
	@Column(name="STR_USUAPROBACION")
	private String usuarioRegistraAprobado;
	
	
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
	private String eliminado = "0";
	
	@JsonIgnore
	@OneToMany(mappedBy="rendicion",cascade = CascadeType.ALL)
	private List<DetalleRendicion> listadoDetallesRendicion;
	
	@Transient
	private String fechaInicioTxt;
	
	public Long getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(Long idRendicion) {
		this.idRendicion = idRendicion;
	}
	public Administrado getAdministrado() {
		return administrado;
	}
	public void setAdministrado(Administrado administrado) {
		this.administrado = administrado;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public float getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getDocumentoReferencia() {
		return documentoReferencia;
	}
	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaRendido() {
		return fechaRendido;
	}
	public void setFechaRendido(Date fechaRendido) {
		this.fechaRendido = fechaRendido;
	}
	public Date getFechaAprobado() {
		return fechaAprobado;
	}
	public void setFechaAprobado(Date fechaAprobado) {
		this.fechaAprobado = fechaAprobado;
	}
	public String getPagado() {
		return pagado;
	}
	public void setPagado(String pagado) {
		this.pagado = pagado;
	}
	
	public String getUsuarioRegistraAprobado() {
		return usuarioRegistraAprobado;
	}
	public void setUsuarioRegistraAprobado(String usuarioRegistraAprobado) {
		this.usuarioRegistraAprobado = usuarioRegistraAprobado;
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
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public List<DetalleRendicion> getListadoDetallesRendicion() {
		return listadoDetallesRendicion;
	}
	public void setListadoDetallesRendicion(List<DetalleRendicion> listadoDetallesRendicion) {
		this.listadoDetallesRendicion = listadoDetallesRendicion;
	}
	public String getFechaInicioTxt() {
		if(this.getFechaInicio() != null ) {
			fechaInicioTxt= FechaUtil.ConvertirFechaDDMMYYYY(this.fechaInicio);
		}
		
		return fechaInicioTxt;
	}
	public void setFechaInicioTxt(String fechaInicioTxt) {
		this.fechaInicioTxt = fechaInicioTxt;
	}
	
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public Long getSedeUnidad() {
		return sedeUnidad;
	}
	public void setSedeUnidad(Long sedeUnidad) {
		this.sedeUnidad = sedeUnidad;
	}
	
	
	
}
