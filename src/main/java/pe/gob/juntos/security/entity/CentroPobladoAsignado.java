package pe.gob.juntos.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.NamedNativeQuery(name ="buscar_cp_asignado", query="call SITC.PKG_ASIGNACION_PERSONAL.FILTRAR_CENTRO_POBLADO(?,:P_CODIGO)",
											callable = true, resultClass = CentroPobladoAsignado.class)
public class CentroPobladoAsignado implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient String idGrid;
	
	@Id
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="C_DEPA")
	private String c_depa;
	
	@Column(name="C_PROV")
	private String c_prov;
	
	@Column(name="C_DIST")
	private String c_dist;
	
	@Column(name="C_POBLADO")
	private String c_poblado;
	
	@Column(name="X_DEPA")
	private String x_depa;
	
	@Column(name="X_PROV")
	private String x_prov;
	
	@Column(name="X_DIST")
	private String x_dist;
	
	@Column(name="X_POBLADO")
	private String x_poblado;

	public String getIdGrid() {
		return idGrid;
	}

	public void setIdGrid(String idGrid) {
		this.idGrid = idGrid;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getC_depa() {
		return c_depa;
	}

	public void setC_depa(String c_depa) {
		this.c_depa = c_depa;
	}

	public String getC_prov() {
		return c_prov;
	}

	public void setC_prov(String c_prov) {
		this.c_prov = c_prov;
	}

	public String getC_dist() {
		return c_dist;
	}

	public void setC_dist(String c_dist) {
		this.c_dist = c_dist;
	}

	public String getC_poblado() {
		return c_poblado;
	}

	public void setC_poblado(String c_poblado) {
		this.c_poblado = c_poblado;
	}

	public String getX_depa() {
		return x_depa;
	}

	public void setX_depa(String x_depa) {
		this.x_depa = x_depa;
	}

	public String getX_prov() {
		return x_prov;
	}

	public void setX_prov(String x_prov) {
		this.x_prov = x_prov;
	}

	public String getX_dist() {
		return x_dist;
	}

	public void setX_dist(String x_dist) {
		this.x_dist = x_dist;
	}

	public String getX_poblado() {
		return x_poblado;
	}

	public void setX_poblado(String x_poblado) {
		this.x_poblado = x_poblado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
