package pe.gob.juntos.security.entity;

import java.io.Serializable;
import java.util.Date;

public class AsignacionPersonal implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sec_asignacion;
	private String c_depa;
	private String c_prov;
	private String c_dist;
	private String c_poblado;
	private String cod_usuario_colaborador;
	private String estado;
	private Date fecha_registro;
	private String usuario_registro;
	private Date fecha_modificacion;
	private String usuario_modificacion;
	
	
	public AsignacionPersonal()
	{	
	}

	public int getSec_asignacion() {
		return sec_asignacion;
	}

	public void setSec_asignacion(int sec_asignacion) {
		this.sec_asignacion = sec_asignacion;
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

	public String getCod_usuario_colaborador() {
		return cod_usuario_colaborador;
	}

	public void setCod_usuario_colaborador(String cod_usuario_colaborador) {
		this.cod_usuario_colaborador = cod_usuario_colaborador;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuario_registro() {
		return usuario_registro;
	}

	public void setUsuario_registro(String usuario_registro) {
		this.usuario_registro = usuario_registro;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getUsuario_modificacion() {
		return usuario_modificacion;
	}

	public void setUsuario_modificacion(String usuario_modificacion) {
		this.usuario_modificacion = usuario_modificacion;
	}

	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
