package pe.gob.juntos.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.gob.juntos.entity.DetalleRendicionCajaChica;

public interface DetalleRendicionCajaChicaRepository extends CrudRepository<DetalleRendicionCajaChica, Long>{
	
	@Query(	" select o from DetalleRendicionCajaChica o "
			+ 	" join o.rendicionCajaChica r "
			+ 	" where r.idRendicionCajaChica =:idRendicionCajaChica ")
	List<DetalleRendicionCajaChica> listarRendicionCajaChica(@Param("idRendicionCajaChica") Long idRendicionCajaChica);

}
