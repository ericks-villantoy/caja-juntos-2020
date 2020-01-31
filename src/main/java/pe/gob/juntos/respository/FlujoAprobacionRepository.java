package pe.gob.juntos.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.gob.juntos.entity.FlujoAprobacion;

public interface FlujoAprobacionRepository extends CrudRepository<FlujoAprobacion, Long>{

	@Query(	" select o from FlujoAprobacion o "
		+ 	" join o.rendicion r "
		+ 	" where r.idRendicion =:idRendicion ")
	List<FlujoAprobacion> buscarFlujosdeAprobacionRendicion(@Param("idRendicion") Long idRendicion);
}
