package pe.gob.juntos.respository;

import org.springframework.data.repository.CrudRepository;

import pe.gob.juntos.entity.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>{

	
}
