package pe.gob.juntos.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.gob.juntos.entity.ControlCierreMesCajaChica;

public interface ControlCierreMesCajaChicaRepository extends CrudRepository<ControlCierreMesCajaChica, Long>{

	@Query(	" select o from ControlCierreMesCajaChica o "
			+ 	" join o.sede s "
			+ 	" where s.idSede =:idSede ")
		public ControlCierreMesCajaChica buscarControlCierre(@Param("idSede") String idSede);
	
	@Query(	" select o from ControlCierreMesCajaChica o "
			+ 	" join o.sede s "
			+ 	" where s.idSede =:idSede and o.anio =:anio and o.mes =:mes ")
		public ControlCierreMesCajaChica buscarControlCierreAnioMes(@Param("idSede") String idSede, @Param("anio") Long anio, @Param("mes") Long mes);
}
