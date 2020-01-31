package pe.gob.juntos.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PerfilUsuarioPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "COD_SISTEMA", nullable = false)
    private String codigoSistema;

    @Column(name = "COD_PERFIL", nullable = false)
    private Long codigoPerfil;
    
	@Column(name = "COD_USUARIO", nullable = false)
	private String codigoUsuario;

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public Long getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(Long codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	
	
}
