package org.dam2.bancoestudiarspring.model.dto;

import org.dam2.bancoestudiarspring.model.data.Cliente;
import org.dam2.bancoestudiarspring.model.data.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CuentaClienteDTOCompleto {
	private ClienteDTO cliente;
	private CuentaDTO cuenta;
	private boolean admin;
}
