package pe.gob.juntos.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.NamedNativeQuery(name = "listar_Perfiles", query = "call SITC.PKG_ASIGNACION_PERSONAL.LISTAR_PERFILES(?)", callable = true, resultClass = Perfiles.class)
public class Perfiles  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="CODIGO_PERFIL")
	private String codigo_perfil;
	
	@Column(name="PERFIL")
	private String perfil;
	public String getCodigo_perfil() {
		return codigo_perfil;
	}
	public void setCodigo_perfil(String codigo_perfil) {
		this.codigo_perfil = codigo_perfil;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	

}
