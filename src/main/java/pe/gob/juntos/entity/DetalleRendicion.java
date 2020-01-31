package pe.gob.juntos.entity;

import java.util.Date;
import java.util.List;

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

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CC_TR_DETALLERENDI")
@Where(clause = "STR_ESBORRADO='0'")
@JsonIgnoreProperties({"listadoRutaDetalleRendicion"})
public class DetalleRendicion {
	
	@Id
	@Column(name="INT_IDDETREND")
	@SequenceGenerator(name="SEQ_DET_RENDICION",sequenceName="SEQ_DETALLE_RENDICION", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DET_RENDICION")
	private Long idDetalleRendicion;
	
	@ManyToOne
	@JoinColumn(name="INT_IDRENDICION")
	private Rendicion rendicion;
	
	@ManyToOne
	@JoinColumn(name="INT_IDESPECIFICA")
	private Especifica especifica;
	
	@ManyToOne
	@JoinColumn(name="INT_IDCLASE")
	private Clase clase;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DTE_FECHA")
	private Date fecha;
	
	@Column(name="STR_SERIERECIBO")
	private String serieRecibo;
	
	@Column(name="STR_NRORECIBO")
	private String numeroRecibo;
	
	@Column(name="STR_RUCEMPRESA")
	private String rucEmpresa;
	
	@Column(name="STR_RAZONSOCIAL")
	private String razonSocial=" ";
	
	@Column(name="INT_IMPORTE")
	private float importe = 0;
	//private Long importe = new Long(0);
	
	@Column(name="STR_DOCREFERENCIA")
	private String documentoReferencia;
	
	@Column(name="INT_CANTFOLIOS")
	private int cantidadFolios;
	
	@Column(name="STR_DETALLE")
	private String detalle;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FECHAPAGADO")
	private Date fechaPagado;
	
	@Column(name="STR_USUREGISTPAGO")
	private String usuarioRegistraPagado=" ";
	
	@Column(name="STR_ESTADO_ENVIO")
	private String estadoEnvio;
	
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
	
	@JsonIgnore
	@OneToMany(mappedBy="detalleRendicion")
	private List<RutaDetalleRendicion> listadoRutaDetalleRendicion;
	
	public Long getIdDetalleRendicion() {
		return idDetalleRendicion;
	}
	public void setIdDetalleRendicion(Long idDetalleRendicion) {
		this.idDetalleRendicion = idDetalleRendicion;
	}
	public Rendicion getRendicion() {
		return rendicion;
	}
	public void setRendicion(Rendicion rendicion) {
		this.rendicion = rendicion;
	}
	public Especifica getEspecifica() {
		return especifica;
	}
	public void setEspecifica(Especifica especifica) {
		this.especifica = especifica;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getSerieRecibo() {
		return serieRecibo;
	}
	public void setSerieRecibo(String serieRecibo) {
		this.serieRecibo = serieRecibo;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public String getRucEmpresa() {
		return rucEmpresa;
	}
	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public String getDocumentoReferencia() {
		return documentoReferencia;
	}
	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}
	public int getCantidadFolios() {
		return cantidadFolios;
	}
	public void setCantidadFolios(int cantidadFolios) {
		this.cantidadFolios = cantidadFolios;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
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
	public Date getFechaPagado() {
		return fechaPagado;
	}
	public void setFechaPagado(Date fechaPagado) {
		this.fechaPagado = fechaPagado;
	}
	public String getUsuarioRegistraPagado() {
		return usuarioRegistraPagado;
	}
	public void setUsuarioRegistraPagado(String usuarioRegistraPagado) {
		this.usuarioRegistraPagado = usuarioRegistraPagado;
	}
	public List<RutaDetalleRendicion> getListadoRutaDetalleRendicion() {
		return listadoRutaDetalleRendicion;
	}
	public void setListadoRutaDetalleRendicion(List<RutaDetalleRendicion> listadoRutaDetalleRendicion) {
		this.listadoRutaDetalleRendicion = listadoRutaDetalleRendicion;
	}
	public String getEstadoEnvio() {
		return estadoEnvio;
	}
	public void setEstadoEnvio(String estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}
	
	
}
