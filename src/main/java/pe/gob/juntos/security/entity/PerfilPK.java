package pe.gob.juntos.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PerfilPK implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Column(name = "COD_SISTEMA", nullable = false)
    private String codigoSistema;

    @Column(name = "COD_PERFIL", nullable = false)
    private String codigoPerfil;
    
    


	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public String getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(String codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}
    
    
	
}
