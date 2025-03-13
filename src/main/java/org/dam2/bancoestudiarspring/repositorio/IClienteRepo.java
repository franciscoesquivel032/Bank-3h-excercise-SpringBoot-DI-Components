package org.dam2.bancoestudiarspring.repositorio;

import org.dam2.bancoestudiarspring.model.data.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepo extends CrudRepository<Cliente, String>{
	
}
