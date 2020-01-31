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
@Table(name = "CC_TR_RENDICAJACHICA")
@Where(clause = "STR_ESBORRADO='0'")
@JsonIgnoreProperties({"listadoDetalleRendicionCajaChica"})
public class RendicionCajaChica {

	@Id
	@Column(name="INT_IDRENDICAJACHICA")
	@SequenceGenerator(name="SEQ_RENDICIONCC",sequenceName="SEQ_RENDICION_CCH", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RENDICIONCC")
	private Long idRendicionCajaChica;
	
	@ManyToOne
	@JoinColumn(name="C_REGION")
	private Sede sede;
	
	@Column(name="STR_ANIO")
	private String anio;
	
	@Column(name="STR_MES")
	private String mes;
	
	@Column(name="DEC_IMPORTETOTAL")
	private Long importe;
	
	@Column(name="NRO_ENVIO")
	private Long nroEnvio;
	
	@Column(name="STR_USURENDICION")
	private String usuarioRendicionCajaChica;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DTE_FECHAREND")
	private Date fechaRendicionCajaChica;
	
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
	
	@JsonIgnore
	@OneToMany(mappedBy="detalleRendicion", cascade = CascadeType.ALL)
	private List<DetalleRendicionCajaChica> listadoDetalleRendicionCajaChica;
	
	@Transient
	private String descripcionRendicionCajachica;
	
	@Transient
	private String fechaRendicionCajaChicaTxt;
	
	
	public Long getIdRendicionCajaChica() {
		return idRendicionCajaChica;
	}
	public void setIdRendicionCajaChica(Long idRendicionCajaChica) {
		this.idRendicionCajaChica = idRendicionCajaChica;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Long getImporte() {
		return importe;
	}
	public void setImporte(Long importe) {
		this.importe = importe;
	}
	public String getUsuarioRendicionCajaChica() {
		return usuarioRendicionCajaChica;
	}
	public void setUsuarioRendicionCajaChica(String usuarioRendicionCajaChica) {
		this.usuarioRendicionCajaChica = usuarioRendicionCajaChica;
	}
	public Date getFechaRendicionCajaChica() {
		return fechaRendicionCajaChica;
	}
	public void setFechaRendicionCajaChica(Date fechaRendicionCajaChica) {
		this.fechaRendicionCajaChica = fechaRendicionCajaChica;
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
	public String getDescripcionRendicionCajachica() {
		if(this.getAnio()!=null && this.getMes()!=null)
			descripcionRendicionCajachica = this.mes+"/"+this.anio;
		return descripcionRendicionCajachica;
	}
	public void setDescripcionRendicionCajachica(String descripcionRendicionCajachica) {
		this.descripcionRendicionCajachica = descripcionRendicionCajachica;
	}
	public String getFechaRendicionCajaChicaTxt() {
		if(this.getFechaRendicionCajaChica()!=null)
			fechaRendicionCajaChicaTxt = FechaUtil.ConvertirFechaDDMMYYYY(this.fechaRendicionCajaChica);
		return fechaRendicionCajaChicaTxt;
	}
	public void setFechaRendicionCajaChicaTxt(String fechaRendicionCajaChicaTxt) {
		this.fechaRendicionCajaChicaTxt = fechaRendicionCajaChicaTxt;
	}
	public List<DetalleRendicionCajaChica> getListadoDetalleRendicionCajaChica() {
		return listadoDetalleRendicionCajaChica;
	}
	public void setListadoDetalleRendicionCajaChica(List<DetalleRendicionCajaChica> listadoDetalleRendicionCajaChica) {
		this.listadoDetalleRendicionCajaChica = listadoDetalleRendicionCajaChica;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public Long getNroEnvio() {
		return nroEnvio;
	}
	public void setNroEnvio(Long nroEnvio) {
		this.nroEnvio = nroEnvio;
	}
	
	
}
