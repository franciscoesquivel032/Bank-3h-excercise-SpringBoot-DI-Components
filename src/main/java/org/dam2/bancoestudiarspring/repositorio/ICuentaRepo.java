package org.dam2.bancoestudiarspring.repositorio;

import org.dam2.bancoestudiarspring.model.data.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaRepo extends CrudRepository<Cuenta, String> {

}
