package pe.gob.juntos.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.gob.juntos.entity.Sede;

@Repository
public interface SedeRepository extends CrudRepository<Sede, String>{

}
