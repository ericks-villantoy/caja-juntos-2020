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
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "CC_TR_RUTAXSEDE")
@Where(clause = "STR_ESBORRADO='0'")
public class RutaSede {

	@Id
	@Column(name="INT_IDRUTAXSEDE")
	@SequenceGenerator(name="SEQ_RUTASEDE",sequenceName="SEQ_RUTASEDE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RUTASEDE")
	private Long idRutaSede;
	
	@ManyToOne
	@JoinColumn(name="C_REGION")
	private Sede sede;
	
	@Column(name="STR_CODRUTA")
	private String codigoRuta;
	
	@Column(name="STR_UBIGEOPARTIDA")
	private String ubigeoPartida;
	
	@Column(name="STR_UBIGEOPARTIDADESCR")
	private String descripcionUbigeoPartida;
	
	@Column(name="STR_UBIGEOLLEGADA")
	private String ubigeoLlegada;
	
	@Column(name="STR_UBIGEOLLEGADADESCR")
	private String descripcionUbigeoLlegada;
	
	@Column(name="INT_TARIFA")
	private int tarifa;
	
	@Column(name="INT_TIEMPO")
	private int tiempo;
	
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
	
	@Transient
	private String descripcionTotal;
	
	public Long getIdRutaSede() {
		return idRutaSede;
	}
	public void setIdRutaSede(Long idRutaSede) {
		this.idRutaSede = idRutaSede;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public String getCodigoRuta() {
		return codigoRuta;
	}
	public void setCodigoRuta(String codigoRuta) {
		this.codigoRuta = codigoRuta;
	}
	public String getUbigeoPartida() {
		return ubigeoPartida;
	}
	public void setUbigeoPartida(String ubigeoPartida) {
		this.ubigeoPartida = ubigeoPartida;
	}
	public String getDescripcionUbigeoPartida() {
		return descripcionUbigeoPartida;
	}
	public void setDescripcionUbigeoPartida(String descripcionUbigeoPartida) {
		this.descripcionUbigeoPartida = descripcionUbigeoPartida;
	}
	public String getUbigeoLlegada() {
		return ubigeoLlegada;
	}
	public void setUbigeoLlegada(String ubigeoLlegada) {
		this.ubigeoLlegada = ubigeoLlegada;
	}
	public String getDescripcionUbigeoLlegada() {
		return descripcionUbigeoLlegada;
	}
	public void setDescripcionUbigeoLlegada(String descripcionUbigeoLlegada) {
		this.descripcionUbigeoLlegada = descripcionUbigeoLlegada;
	}
	public int getTarifa() {
		return tarifa;
	}
	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
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
	public String getDescripcionTotal() {
		if(this.getDescripcionUbigeoLlegada()!=null && this.getDescripcionUbigeoPartida()!=null) {
			descripcionTotal = "RUTA "+ this.codigoRuta+ ": "+ this.descripcionUbigeoPartida + " - " + this.descripcionUbigeoLlegada + " ( S/. "+ this.tarifa + " )";
		}
		return descripcionTotal;
	}
	public void setDescripcionTotal(String descripcionTotal) {
		this.descripcionTotal = descripcionTotal;
	}
	
	
	
}
