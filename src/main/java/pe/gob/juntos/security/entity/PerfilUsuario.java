package pe.gob.juntos.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tperfilxusuario")
public class PerfilUsuario   implements java.io.Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@JoinColumns(value = {@JoinColumn(name = "COD_PERFIL", referencedColumnName = "COD_PERFIL"), @JoinColumn(name = "COD_SISTEMA", referencedColumnName = "COD_SISTEMA")})
    @ManyToOne(optional = false)
	private Perfil perfil;
	
	@Id
	@Column(name = "COD_USUARIO")
	private String codigoUsuario;
	
	@Column(name = "FLG_ESTADO")
	private String estado;

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	
	
}
