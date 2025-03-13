package org.dam2.bancoestudiarspring.servicio;

import java.util.Optional;

import org.dam2.bancoestudiarspring.configuracion.DtoConverter;
import org.dam2.bancoestudiarspring.model.data.Cliente;
import org.dam2.bancoestudiarspring.model.dto.ClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.ClienteDTOCompleto;
import org.dam2.bancoestudiarspring.repositorio.IClienteRepo;
import org.dam2.bancoestudiarspring.repositorio.ICuentaClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicioImp implements IClienteServicio {

	@Autowired private IClienteRepo clienteDAO;
	@Autowired private ICuentaClienteRepo cuentaclienteDAO;
	@Autowired private DtoConverter mapper;
	
	@Override
	public boolean insertConTfnos(ClienteDTOCompleto cliente) {
		boolean exito = false;
		
		if(clienteDAO.existsById(cliente.getNif())) {
			clienteDAO.save(mapper.map(cliente, Cliente.class));
			exito = true;
		}
		
		return exito;
	}

	@Override
	public Optional<ClienteDTO> findById(String nif) {
	
		return clienteDAO.findById(nif).map(c -> mapper.map(c, ClienteDTO.class));
	}

	@Override
	public boolean isAdmin(String nif, String numero) {
		
		return cuentaclienteDAO.isAdmin(nif, nif);
	}

}
