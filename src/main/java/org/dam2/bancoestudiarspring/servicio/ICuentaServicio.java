package org.dam2.bancoestudiarspring.servicio;

import java.util.Optional;

import org.dam2.bancoestudiarspring.model.data.Cuenta;
import org.dam2.bancoestudiarspring.model.dto.CuentaDTO;
import org.dam2.bancoestudiarspring.model.dto.RetiroDTO;

public interface ICuentaServicio {
	Optional<CuentaDTO> findById(String numero);
	float ingresar(String numero, float cantidad);
	float retirar(String numero, float cantidad);
	boolean crearCuenta(CuentaDTO cuenta);

}
