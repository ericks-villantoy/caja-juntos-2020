package pe.gob.juntos.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.gob.juntos.entity.RutaSede;

public interface RutaSedeRepository extends CrudRepository<RutaSede, Long>{

	@Query( 	" select o from RutaSede o "
			+ 	" join o.sede s "
			+ 	" where s.idSede =:codigoSede")
	List<RutaSede> obtenerTarifarioSede (@Param("codigoSede") String codigoSede);
	
}
