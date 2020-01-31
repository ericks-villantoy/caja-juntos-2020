package pe.gob.juntos.respository;

import org.springframework.data.repository.CrudRepository;

import pe.gob.juntos.security.entity.Perfil;
import pe.gob.juntos.security.entity.PerfilPK;

public interface PerfilRepository extends CrudRepository<Perfil, PerfilPK> {

	
	
}
