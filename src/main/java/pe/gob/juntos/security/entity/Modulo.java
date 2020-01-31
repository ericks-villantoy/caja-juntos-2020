package pe.gob.juntos.security.entity;

public class Modulo 
{
	private int secmodulo;
	private String nombre;
	private String alias;
	private String estado;
	private String cod_usuario_registra;
	private String fecha_registro;
	
	
	public int getSecmodulo() {
		return secmodulo;
	}
	public void setSecmodulo(int secmodulo) {
		this.secmodulo = secmodulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCod_usuario_registra() {
		return cod_usuario_registra;
	}
	public void setCod_usuario_registra(String cod_usuario_registra) {
		this.cod_usuario_registra = cod_usuario_registra;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	

}
