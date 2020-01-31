package pe.gob.juntos.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.gob.juntos.entity.RutaDetalleRendicion;

public interface RutaRendicionDetalleRepository extends CrudRepository<RutaDetalleRendicion, Long>{

	@Query("	select o from RutaDetalleRendicion o "
			+ " join o.detalleRendicion	d "
			+ " where d.idDetalleRendicion =:idDetalleRendicion and o.eliminado='0'	and d.eliminado = '0' ")
	public List<RutaDetalleRendicion> obtenerRutaRendicionDetallePorDetalle(@Param("idDetalleRendicion") Long idDetalleRendicion);
	
}
