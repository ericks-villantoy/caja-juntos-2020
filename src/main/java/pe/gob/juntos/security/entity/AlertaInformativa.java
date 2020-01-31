package pe.gob.juntos.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
@org.hibernate.annotations.NamedNativeQuery(name = "listarMensajesSitc", query = "call SITC.general.sp_listar_mensajes_sitc(?,:vCODUSER)", callable = true, resultClass = AlertaInformativa.class)
public class AlertaInformativa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name = "secmensaje")
	private Long id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "mensaje")
	private String mensaje;
	
	@Column(name = "fecha_vencimiento")
	private String fechaVencimiento;
	
	@Column(name = "estado")
	private Boolean estado;
	
	public AlertaInformativa() {
		super();	
	}
	
	public AlertaInformativa(Long id, String titulo,  String mensaje) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		this.titulo = titulo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
	

}
