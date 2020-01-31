package pe.gob.juntos.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.juntos.entity.RendicionCajaChica;

@Repository
public interface RendicionCajaChicaRepository extends CrudRepository<RendicionCajaChica, Long>{
	
	@Query(" select o from RendicionCajaChica o"
			+ " join o.sede s where o.anio=:anio and o.mes=:mes and s.idSede =:codRegion ")
	List<RendicionCajaChica> listarRendicionesMensualesSede(@Param("anio") String anio, @Param("mes") String mes,@Param("codRegion") String codRegion);

}
