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
@Table(name = "CC_TR_APERTURA_CIERRE")
@Where(clause = "STR_ESBORRADO='0'")
//@JsonIgnoreProperties({"listadoAperturaCierre"})
public class AperturaCierre {
	
	@Id
	@Column(name="INT_IDAPERTURA_CIERRE")
	@SequenceGenerator(name="SEQ_APERTURA_CIERRE",sequenceName="SEQ_APERTURA_CIERRE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_APERTURA_CIERRE")
	private Long idAperturaCierre;
	
	@ManyToOne
	@JoinColumn(name="C_REGION")
	private Sede sede;
	
	@ManyToOne
	@JoinColumn(name="INT_IDESPECIFICA")
	private Especifica especifica;
	
	@Column(name="INT_ANIO")
	private Long anio;
	
	@Column(name="DEC_MONTO_APERTURA")
	private float montoApertura;
	
	@Column(name="DEC_MONTO_LIQUIDACION")
	private float montoLiquidacion;
	
	@Column(name="DEC_MONTO_EJECUCION")
	private Double montoEjecucion;
	
	@Column(name="DEC_SALDO")
	private Double saldo;
	
	@Column(name="STR_FLAG_CERRADO")
	private String flagCerrado;
	
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

	public Long getIdAperturaCierre() {
		return idAperturaCierre;
	}

	public void setIdAperturaCierre(Long idAperturaCierre) {
		this.idAperturaCierre = idAperturaCierre;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Especifica getEspecifica() {
		return especifica;
	}

	public void setEspecifica(Especifica especifica) {
		this.especifica = especifica;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public float getMontoApertura() {
		return montoApertura;
	}

	public void setMontoApertura(float montoApertura) {
		this.montoApertura = montoApertura;
	}

	public float getMontoLiquidacion() {
		return montoLiquidacion;
	}

	public void setMontoLiquidacion(float montoLiquidacion) {
		this.montoLiquidacion = montoLiquidacion;
	}

	public Double getMontoEjecucion() {
		return montoEjecucion;
	}

	public void setMontoEjecucion(Double montoEjecucion) {
		this.montoEjecucion = montoEjecucion;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getFlagCerrado() {
		return flagCerrado;
	}

	public void setFlagCerrado(String flagCerrado) {
		this.flagCerrado = flagCerrado;
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
	/*		
	@JsonIgnore
	@OneToMany(mappedBy="aperturaCierre",cascade = CascadeType.ALL)
	private List<DetalleRendicion> listadoDetallesRendicion;
	*/
	
}
