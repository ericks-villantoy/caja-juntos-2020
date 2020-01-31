package pe.gob.juntos.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import pe.gob.juntos.entity.VistaAccesoSeguridad;
import pe.gob.juntos.respository.PerfilUsuarioRepository;
import pe.gob.juntos.respository.VistaAccesoSeguridadRepository;
import pe.gob.juntos.security.entity.Perfil;
import pe.gob.juntos.security.entity.PerfilUsuario;
import pe.gob.juntos.security.entity.UsuarioSeguridad;
import pe.gob.juntos.util.CadenaUtil;


@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private VistaAccesoSeguridadRepository vistaAccesoSeguridadRepository;

	@Autowired
	private PerfilUsuarioRepository perfilUsuarioRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsuarioSeguridad mUsuarioSeguridad = new UsuarioSeguridad();

		UsernamePasswordAuthenticationToken mUserReturn=null;

		String user = authentication.getName();
		String password = authentication.getCredentials().toString();

		String encriptado = CadenaUtil.getMD5(password);



		try {
			VistaAccesoSeguridad vistaAcceso = vistaAccesoSeguridadRepository.obtenerAutenticacion(user.toUpperCase(),encriptado);
			if(vistaAcceso==null) {
				logger.error("No se encontro usuario  :" + user);
				return null;
			}

			mUsuarioSeguridad.setUsername(user);
			mUsuarioSeguridad.setNrodni(vistaAcceso.getNumeroDocumento());
			mUsuarioSeguridad.setCodigoUsuario(vistaAcceso.getCodigoUsuario());
			mUsuarioSeguridad.setPrimerApellido(vistaAcceso.getApellidoPaterno());
			mUsuarioSeguridad.setSegundoApellido(vistaAcceso.getApellidoMaterno());
			mUsuarioSeguridad.setNombres(vistaAcceso.getNombres());
			mUsuarioSeguridad.setCodigoUnidadTerritorial(vistaAcceso.getCodigoRegion());
			mUsuarioSeguridad.setDescripcionUnidadTerritorial(vistaAcceso.getDescripcionRegion());
			mUsuarioSeguridad.setCorreoElectronico(vistaAcceso.getEmail());

			List<PerfilUsuario> listaPerfilUsuario = perfilUsuarioRepository.obtenerPerfilesAutenticacion(vistaAcceso.getCodigoUsuario());

			/*List<Perfil> listaPerfil = listaPerfilUsuario.stream()
					.map(t->t.getPerfil())
					.distinct()
					.collect(Collectors.toList());*/ //solo para java 8 en adelante
			
			List<Perfil> listaPerfil = new ArrayList<Perfil>();

			for(PerfilUsuario pu : listaPerfilUsuario) {
				listaPerfil.add(pu.getPerfil());
			}

			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();	

			if(!listaPerfil.isEmpty()){
				
				authorities.add(new SimpleGrantedAuthority("JUNTOS"));
				
				for(Perfil perfil : listaPerfil){
					String CODIGO = perfil.getPerfilPK().getCodigoPerfil()==null?"":perfil.getPerfilPK().getCodigoPerfil().toUpperCase();
					authorities.add(new SimpleGrantedAuthority("ROLE_"+CODIGO));
				}

			}else{
				authorities.add(new SimpleGrantedAuthority("ROLE_INVITADO"));
			}

			mUsuarioSeguridad.setAccountNonExpired(true);
			mUsuarioSeguridad.setAccountNonLocked(true);
			mUsuarioSeguridad.setCredentialsNonExpired(true);
			mUsuarioSeguridad.setEnabled(true);	    

			mUsuarioSeguridad.setAuthorities(authorities);
			mUsuarioSeguridad.setPerfiles(listaPerfil);

			mUserReturn = new UsernamePasswordAuthenticationToken(
					mUsuarioSeguridad, mUsuarioSeguridad.getUsername(),mUsuarioSeguridad.getAuthorities()) ;

			return mUserReturn;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		boolean soportado = authentication.equals(
				UsernamePasswordAuthenticationToken.class);

		return soportado;
	}
}
