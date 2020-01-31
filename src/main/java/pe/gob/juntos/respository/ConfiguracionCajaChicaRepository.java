package pe.gob.juntos.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.gob.juntos.entity.ConfiguracionCajaChica;

public interface ConfiguracionCajaChicaRepository extends CrudRepository<ConfiguracionCajaChica, Long>{

	@Query(	" select o from ConfiguracionCajaChica o "
		+ 	" join o.sede s "
		+ 	" where s.idSede =:idSede ")
	public ConfiguracionCajaChica buscarConfiguracion(@Param("idSede") String idSede);
}
