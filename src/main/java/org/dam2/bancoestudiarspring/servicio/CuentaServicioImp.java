package org.dam2.bancoestudiarspring.servicio;

import java.util.Optional;

import org.dam2.bancoestudiarspring.configuracion.DtoConverter;
import org.dam2.bancoestudiarspring.model.data.Cuenta;
import org.dam2.bancoestudiarspring.model.dto.CuentaDTO;
import org.dam2.bancoestudiarspring.model.dto.RetiroDTO;
import org.dam2.bancoestudiarspring.repositorio.ICuentaClienteRepo;
import org.dam2.bancoestudiarspring.repositorio.ICuentaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
@Service
public class CuentaServicioImp implements ICuentaServicio {

	@Autowired DtoConverter mapper;
	@Autowired ICuentaRepo cuentaDAO;
	@Autowired ICuentaClienteRepo cuentaClienteDAO;
	
	@Override
	public Optional<CuentaDTO> findById(String numero) {
	
		return cuentaDAO.findById(numero).map(c -> mapper.map(c, CuentaDTO.class));
	}

	@Transactional
	@Override
	public float ingresar(String numero, float cantidad) {
		Optional<Cuenta> cuenta;
		Cuenta c;
		float nuevoSaldo = 0;
		
		if(cantidad > 0)
		{
			cuenta = cuentaDAO.findById(numero);
			
			if(cuenta.isPresent()) {
				c = cuenta.get();
				c.setSaldo(c.getSaldo() + cantidad);
				cuentaDAO.save(c);
				nuevoSaldo = c.getSaldo();
			}
		}
		
		return nuevoSaldo;
	}

	@Transactional
	@Override
	public float retirar(String numero, float cantidad) {
		Optional<Cuenta> cuenta;
		Cuenta c;
		float respuesta = 0;
		
		if(cantidad > 0) {
			cuenta = cuentaDAO.findById(numero);
			if(cuenta.isPresent()) {
				c = cuenta.get();
				if(c.getSaldo() - cantidad > condicion(numero)) {
					c.setSaldo(c.getSaldo() - cantidad);
					cuentaDAO.save(c);
					respuesta = c.getSaldo();
				} else {
					throw new IllegalStateException("Saldo insuficiente... Saldo máximo a retirar: " + (c.getSaldo() - condicion(numero)));
				}
			} else {
				throw new IllegalArgumentException("No se ha encontrado cuenta...");
			}
		} else {
			throw new IllegalArgumentException("Cantidad negativa...");
		}

		return respuesta;
	}

	
	
	private float condicion(String numero) {
		
		return - (cuentaClienteDAO.getMaxAvalesPorCuenta(numero) / 2);
	}

	@Override
	public boolean crearCuenta(CuentaDTO cuenta) {
		boolean exito = false;
		
		if(!cuentaDAO.existsById(cuenta.getNumero()) && cuenta.getSaldo() > 0) {
			cuentaDAO.save(mapper.map(cuenta, Cuenta.class));
			exito = true;
		}
	
		return exito;
	}

}
