package org.dam2.bancoestudiarspring.repositorio;

import java.util.List;
import java.util.Optional;

import org.dam2.bancoestudiarspring.model.data.Cliente;
import org.dam2.bancoestudiarspring.model.data.CuentaCliente;
import org.dam2.bancoestudiarspring.model.dto.CuentaClienteDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaClienteRepo extends CrudRepository<CuentaCliente, Integer> {
	@Query("SELECT cc.cliente FROM CuentaCliente cc WHERE cc.cuenta.numero = ?1")
	List<Cliente> findClientesByCuenta(String numero);
	
	@Query("SELECT cc.admin FROM CuentaCliente cc WHERE cc.cliente = ?1 AND cc.cuenta = ?2")
	boolean isAdmin(String cliente, String cuenta);
	
	@Query("SELECT SUM(cc.cliente.maxAvales) FROM CuentaCliente cc WHERE cc.cuenta.numero = ?1")
	Float getMaxAvalesPorCuenta(String numero);
	
	@Query("SELECT cc FROM CuentaCliente cc WHERE cc.cliente.nif = ?1 AND cc.cuenta.numero = ?2")
	Optional<CuentaCliente> findByClienteAndCuenta(String cliente, String cuenta);
}
