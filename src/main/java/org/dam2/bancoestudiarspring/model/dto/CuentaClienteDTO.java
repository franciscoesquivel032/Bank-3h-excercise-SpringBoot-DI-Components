package org.dam2.bancoestudiarspring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class CuentaClienteDTO {
	@EqualsAndHashCode.Include
	private String cuenta;
	@EqualsAndHashCode.Include
	private String cliente;
	private boolean admin;
}
