package pe.gob.juntos.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.gob.juntos.security.entity.PerfilUsuario;

public interface PerfilUsuarioRepository extends CrudRepository<PerfilUsuario, String> {

	@Query( "	select o from PerfilUsuario o"
			+ " join o.perfil p "
			+ " where o.codigoUsuario=:user and p.perfilPK.codigoSistema='030' and p.estado='A' " + 
			  " and  o.estado='A' ")
	List<PerfilUsuario> obtenerPerfilesAutenticacion (@Param("user") String user);
}
