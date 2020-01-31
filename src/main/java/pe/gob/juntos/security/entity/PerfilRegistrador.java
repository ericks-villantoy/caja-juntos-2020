package pe.gob.juntos.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.NamedNativeQuery(name ="buscar_asignacion_perfil", query="call SITC.PKG_ASIGNACION_PERSONAL.FILTRAR_PERSONAL_PERFIL(?,:P_COD_USUARIO,:P_X_NOMBRES,:P_X_APPATERNO,:P_X_APMATERNO,:P_NRODNI,:P_C_DEPA,:P_COD_PERFIL)",
											callable = true, resultClass = PerfilRegistrador.class)
public class PerfilRegistrador implements Serializable
{
	private static final long serialVersionUID = 1L;
	private transient String idGrid;
	
	
	@Column(name="PERFIL")
	private String perfil;
	
	@Column(name="NOMBRES")
	private String nombres;
	
	@Column(name="APELLIDO_PATERNO")
	private String apellido_paterno;
	
	public String getIdGrid() {
		return idGrid;
	}
	public void setIdGrid(String idGrid) {
		this.idGrid = idGrid;
	}
	@Column(name="APELLIDO_MATERNO")
	private String apellido_materno;
	
	@Column(name="DNI")
	private String dni;
	
	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="FECHA_CREACION")
	private String fecha_creacion;
	@Id
	@Column(name="COD_USUARIO")
	private String cod_usuario;
	
	@Column(name="COD_PERFIL")
	private String cod_perfil;
	
	@Column(name="COD_REGION")
	private String cod_region;
	
	@Column(name="NOMBRE_REGION")
	private String nombre_region;
	
	@Column(name="COD_DEPARTAMENTO")
	private String cod_departamento;
	
	@Column(name="NOMBRE_DEPARTAMENTO")
	private String nombre_departamento;
	

	@Column(name="ESTADO_ASIGNACION")
	private String estado_asignacion;
	
	@Column(name="FECHA_ULTIMA_MODIFICACION")
	private String fecha_ultima_modificacion;
	
	
	
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public String getEstado_asignacion() {
		return estado_asignacion;
	}
	public void setEstado_asignacion(String estado_asignacion) {
		this.estado_asignacion = estado_asignacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public void setFec_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getCod_usuario() {
		return cod_usuario;
	}
	public void setCod_usuario(String cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	public String getCod_perfil() {
		return cod_perfil;
	}
	public void setCod_perfil(String cod_perfil) {
		this.cod_perfil = cod_perfil;
	}
	public String getCod_region() {
		return cod_region;
	}
	public void setCod_region(String cod_region) {
		this.cod_region = cod_region;
	}
	public String getNombre_region() {
		return nombre_region;
	}
	public void setNombre_region(String nombre_region) {
		this.nombre_region = nombre_region;
	}
	public String getCod_departamento() {
		return cod_departamento;
	}
	public void setCod_departamento(String cod_departamento) {
		this.cod_departamento = cod_departamento;
	}
	public String getNombre_departamento() {
		return nombre_departamento;
	}
	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
	}
	
	public String getFecha_ultima_modificacion() {
		return fecha_ultima_modificacion;
	}
	public void setFecha_ultima_modificacion(String fecha_ultima_modificacion) {
		this.fecha_ultima_modificacion = fecha_ultima_modificacion;
	}
}
