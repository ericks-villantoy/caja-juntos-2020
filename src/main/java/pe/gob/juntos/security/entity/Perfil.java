package pe.gob.juntos.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TPERFIL")
public class Perfil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected PerfilPK perfilPK;
	
	@Column(name = "DES_PERFIL")
	private String descripcionPerfil;
	
	@Column(name = "FLG_ESTADO")
	private String estado;
	
	@Column(name = "FLG_NIVEL")
	private String nivel;


	public String getDescripcionPerfil() {
		return descripcionPerfil;
	}

	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public PerfilPK getPerfilPK() {
		return perfilPK;
	}

	public void setPerfilPK(PerfilPK perfilPK) {
		this.perfilPK = perfilPK;
	}
	
	
	
	
}
