package org.dam2.bancoestudiarspring.servicio;

import java.util.List;
import java.util.Optional;

import org.dam2.bancoestudiarspring.configuracion.DtoConverter;
import org.dam2.bancoestudiarspring.model.data.Cliente;
import org.dam2.bancoestudiarspring.model.data.Cuenta;
import org.dam2.bancoestudiarspring.model.data.CuentaCliente;
import org.dam2.bancoestudiarspring.model.dto.ClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.CuentaClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.CuentaClienteDTOCompleto;
import org.dam2.bancoestudiarspring.model.dto.CuentaDTO;
import org.dam2.bancoestudiarspring.repositorio.IClienteRepo;
import org.dam2.bancoestudiarspring.repositorio.ICuentaClienteRepo;
import org.dam2.bancoestudiarspring.repositorio.ICuentaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CuentaClienteServicioImp implements ICuentaClienteServicio {

	@Autowired private ICuentaRepo cuentaDAO;
	@Autowired private IClienteRepo clienteDAO;
	@Autowired private ICuentaClienteRepo ccDAO;
	@Autowired private DtoConverter mapper;
	
	@Override
	public Optional<CuentaClienteDTO> findById(Integer id) {
		
		return ccDAO.findById(id).map(cc -> mapper.map(cc, CuentaClienteDTO.class));
	}

	@Override
	public Integer insert(CuentaClienteDTO cc) {
		CuentaCliente c;
		int id = -1;
		
		if(clienteDAO.existsById(cc.getCliente()) && cuentaDAO.existsById(cc.getCuenta())) {
			c = ccDAO.save(mapper.map(cc, CuentaCliente.class));
			id = c.getId();
		}

		return id;
	}

	@Override
	public Optional<CuentaClienteDTO> findByClienteAndCuenta(String cliente, String cuenta) {
		return ccDAO.findByClienteAndCuenta(cliente, cuenta).map(c -> mapper.map(c, CuentaClienteDTO.class));
	}

	@Override
	public List<ClienteDTO> findClientesByCuenta(String numero) {
		return mapper.mapAll(ccDAO.findClientesByCuenta(numero), ClienteDTO.class);
	}

	@Override
	public boolean isAdmin(String cliente, String cuenta) {
		return ccDAO.isAdmin(cliente, cuenta);
	}

	@Transactional
	@Override
	public Integer insertCompleto(CuentaClienteDTOCompleto cc) {
		Optional<ClienteDTO> cOpt;
		Optional<CuentaDTO> cuOpt;
		ClienteDTO c;
		CuentaDTO cuenta;
		CuentaCliente cucl = new CuentaCliente();
		int response = 0;
		
		
		cOpt = clienteDAO.findById(cc.getCliente().getNif()).map(cli -> mapper.map(cli, ClienteDTO.class));
		
		c = cOpt.orElse(mapper.map(clienteDAO.save(Cliente.builder()
				.nif(cc.getCliente().getNif())
				.nombre(cc.getCliente().getNombre())
				.maxAvales(cc.getCliente().getMaxAvales())
				.build()), ClienteDTO.class));
		
		cucl.setCliente(mapper.map(c, Cliente.class));
		
		cuOpt = cuentaDAO.findById(cc.getCuenta().getNumero()).map(cuen -> mapper.map(cuen, CuentaDTO.class));
		
		cuenta = cuOpt.orElse(mapper.map(cuentaDAO.save(Cuenta.builder()
					.numero(cc.getCuenta().getNumero())
					.saldo(cc.getCuenta().getSaldo())
					.build()
					), CuentaDTO.class));
		
		cucl.setCuenta(mapper.map(cuenta, Cuenta.class));
		cucl.setAdmin(cc.isAdmin());
		
		response = ccDAO.save(cucl).getId();
		
		return response;
	}

	
	
}
