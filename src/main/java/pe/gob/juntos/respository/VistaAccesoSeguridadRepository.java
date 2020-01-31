package pe.gob.juntos.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.juntos.entity.VistaAccesoSeguridad;

@Repository("vistaAccesoSeguridadRepository")
public interface VistaAccesoSeguridadRepository extends CrudRepository<VistaAccesoSeguridad, String> {

	@Query( "select o from VistaAccesoSeguridad o where lower(o.usuario) =lower(:user) and o.clave =:clave and o.estado='A' ")
	VistaAccesoSeguridad obtenerAutenticacion (@Param("user") String user, @Param("clave") String clave);
}
