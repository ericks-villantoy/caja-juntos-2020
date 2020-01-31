package pe.gob.juntos.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.gob.juntos.entity.DetalleRendicion;

public interface DetalleRendicionRepository extends CrudRepository<DetalleRendicion, Long> {

	@Query("	select o from DetalleRendicion o "			
			+ " where o.rendicion.sede.idSede=:codRegion and o.rendicion.vigente='1'  and o.rendicion.eliminado='0' "
			+ " and o.especifica.idEspecifica=:idEspecifica and o.vigente='1'  and o.eliminado='0' "
			)
	public List<DetalleRendicion> obtenerDetalleRendicionByEspecificaYregion(@Param("idEspecifica") Long idEspecifica,@Param("codRegion") String codRegion);
}
