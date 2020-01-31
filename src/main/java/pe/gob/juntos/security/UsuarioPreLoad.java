package pe.gob.juntos.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import pe.gob.juntos.security.entity.Perfil;

public class UsuarioPreLoad{

	private String password;
	private String username;

	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	private String codigoUsuario;	
	private String nombres;
	private String primerApellido;
	private String segundoApellido;
	private String codigoUnidadTerritorial;
	private String descripcionUnidadTerritorial;
	
	private String correoElectronico;
	private String nrodni;
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	private Collection<GrantedAuthority> authorities;
	private List<Perfil> perfiles;
	
	private transient String mensaje;
	
	public String getNombreCompleto() {
		return getPrimerApellido()+" "+getSegundoApellido()+", "+ getNombres();
	}
	
	public String getDescripcionUnidadTerritorial() {
		return descripcionUnidadTerritorial;
	}

	public void setDescripcionUnidadTerritorial(String descripcionUnidadTerritorial) {
		this.descripcionUnidadTerritorial = descripcionUnidadTerritorial;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}


	

	public String getCodigoUnidadTerritorial() {
		return codigoUnidadTerritorial;
	}

	public void setCodigoUnidadTerritorial(String codigoUnidadTerritorial) {
		this.codigoUnidadTerritorial = codigoUnidadTerritorial;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNrodni() {
		return nrodni;
	}

	public void setNrodni(String nrodni) {
		this.nrodni = nrodni;
	}
	
	

}
