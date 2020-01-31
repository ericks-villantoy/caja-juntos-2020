package pe.gob.juntos.security.entity;

public class Acceso 
{
	private long secingreso;
	private int secmodulo;
	private String idsession;
	private String cod_usuario_registro;
	private String cod_perfil;
	private String c_region;
	private String fecha_ingreso;
	private String fecha_salida;
	
	
	public long getSecingreso() {
		return secingreso;
	}
	public void setSecingreso(long secingreso) {
		this.secingreso = secingreso;
	}
	public int getSecmodulo() {
		return secmodulo;
	}
	public void setSecmodulo(int secmodulo) {
		this.secmodulo = secmodulo;
	}
	public String getIdsession() {
		return idsession;
	}
	public void setIdsession(String idsession) {
		this.idsession = idsession;
	}
	public String getCod_usuario_registro() {
		return cod_usuario_registro;
	}
	public void setCod_usuario_registro(String cod_usuario_registro) {
		this.cod_usuario_registro = cod_usuario_registro;
	}
	public String getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public String getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	public String getCod_perfil() {
		return cod_perfil;
	}
	public void setCod_perfil(String cod_perfil) {
		this.cod_perfil = cod_perfil;
	}
	public String getC_region() {
		return c_region;
	}
	public void setC_region(String c_region) {
		this.c_region = c_region;
	}
	
	
	
	
}
