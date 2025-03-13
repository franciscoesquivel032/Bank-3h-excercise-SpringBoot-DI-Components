package org.dam2.bancoestudiarspring.servicio;

import java.util.Optional;

import org.dam2.bancoestudiarspring.model.dto.ClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.ClienteDTOCompleto;

public interface IClienteServicio {
	boolean insertConTfnos(ClienteDTOCompleto cliente);
	Optional<ClienteDTO> findById(String nif);
	boolean isAdmin(String nif, String numero);
}
