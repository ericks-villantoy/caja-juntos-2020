package pe.gob.juntos.util;

import java.util.Collection;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import pe.gob.juntos.security.entity.UsuarioSeguridad;

public class SeguridadUtil {

	
	@SuppressWarnings("unchecked")
	public static boolean hasRole(String role) {
		
		  Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
		  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		  boolean hasRole = false;
		  for (GrantedAuthority authority : authorities) {
		     hasRole = authority.getAuthority().equals(role);
		     if (hasRole) {
			  break;
		     }
		  }
		  return hasRole;
		}
	
	public static UsuarioSeguridad obtenerUsuarioSeguridad(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioSeguridad usuarioSeg = (UsuarioSeguridad)auth.getPrincipal();
				
		return usuarioSeg;
		
	}
	
	public static String obtenerUsuario(){
		String usuario="";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!String.class.isInstance(auth.getPrincipal())){
		
		UsuarioSeguridad usuarioSeg = (UsuarioSeguridad)auth.getPrincipal();
			usuario= usuarioSeg.getUsername();
		}
		return usuario;
	}
	
	public static String obtenerCodigoUsuario(){
		String usuario="";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!String.class.isInstance(auth.getPrincipal())){
		
		UsuarioSeguridad usuarioSeg = (UsuarioSeguridad)auth.getPrincipal();
			usuario= usuarioSeg.getCodigoUsuario();
		}
		return usuario;
	}
	
	public static String obtenerRegion(){
		String ubigeo="";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!String.class.isInstance(auth.getPrincipal())){
		
		UsuarioSeguridad usuarioSeg = (UsuarioSeguridad)auth.getPrincipal();
			ubigeo= usuarioSeg.getCodigoUnidadTerritorial();
		}
		return ubigeo;
	}
	
	
	public static boolean isAnonymousAuthenticationToken(){
		boolean anonymous = true;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken))
			anonymous=false;
		return anonymous;
		
	}
}
