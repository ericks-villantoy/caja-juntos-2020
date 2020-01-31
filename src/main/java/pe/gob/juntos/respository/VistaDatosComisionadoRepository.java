package pe.gob.juntos.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.juntos.entity.VistaDatosComisionado;

@Repository("vistaDatosComisionadoRepository")
public interface VistaDatosComisionadoRepository extends CrudRepository<VistaDatosComisionado, String> {
	
	@Query( "select o from VistaDatosComisionado o where o.usuario =:user")
	VistaDatosComisionado obtenerComisionadoUsuario (@Param("user") String user);

}
