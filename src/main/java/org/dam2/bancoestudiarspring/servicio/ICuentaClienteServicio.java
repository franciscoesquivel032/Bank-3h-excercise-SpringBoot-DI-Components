package org.dam2.bancoestudiarspring.servicio;

import java.util.List;
import java.util.Optional;

import org.dam2.bancoestudiarspring.model.data.Cliente;
import org.dam2.bancoestudiarspring.model.dto.ClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.CuentaClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.CuentaClienteDTOCompleto;
import org.springframework.data.jpa.repository.Query;

public interface ICuentaClienteServicio {
	Optional<CuentaClienteDTO> findById(Integer id);
	Integer insert(CuentaClienteDTO cc);
	Optional<CuentaClienteDTO> findByClienteAndCuenta(String cliente, String cuenta);
	List<ClienteDTO> findClientesByCuenta(String numero);
	boolean isAdmin(String cliente, String cuenta);
	Integer insertCompleto(CuentaClienteDTOCompleto cc);
}
