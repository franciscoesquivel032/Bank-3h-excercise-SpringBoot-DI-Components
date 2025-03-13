package org.dam2.bancoestudiarspring.model.data;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

@Entity
public class Tfno {
	@Id
	@NonNull
	@EqualsAndHashCode.Include
	@Column(length = 9)
	private String numero;
	@Column(length = 15)
	private String compania;
}
