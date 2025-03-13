package org.dam2.bancoestudiarspring.model.dto;

import java.util.Set;

import org.dam2.bancoestudiarspring.model.data.Cliente;
import org.dam2.bancoestudiarspring.model.data.Tfno;

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

public class ClienteDTOCompleto {
	@EqualsAndHashCode.Include
	private String nif;
	private String nombre;
	private float maxAvales;
	private Set<Tfno> tfnos;
}
