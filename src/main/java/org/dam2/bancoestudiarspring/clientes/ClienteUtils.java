package org.dam2.bancoestudiarspring.clientes;

import java.util.Optional;

import org.dam2.bancoestudiarspring.model.dto.ClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.CuentaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ClienteUtils {

	public static final String URL = "http://localhost:8080/banco";

	public static Optional<ClienteDTO> buscarCliente(String nif) {
		String URL = ClienteUtils.URL + "/cliente/find/{nif}";
		ClienteDTO c = null;
		RestTemplate template = new RestTemplate();
		
		try {
			
			ResponseEntity<ClienteDTO> response = template.getForEntity(URL, ClienteDTO.class, nif);
			if(response.getStatusCode().is2xxSuccessful()) {
				c = response.getBody();
			}
			
		} catch (HttpClientErrorException e) {
			System.out.println("Error buscando cliente..." + e.getMessage());
		}
		
		
		return Optional.ofNullable(c);
	}
	
	public static Optional<CuentaDTO> buscarCuenta(String numCuenta) {
		String URL = ClienteUtils.URL + "/cuenta/find/{numero}";
		RestTemplate template = new RestTemplate();
		CuentaDTO c = null;
		
		try {
			
			ResponseEntity<CuentaDTO> response = template.getForEntity(URL, CuentaDTO.class, numCuenta);
			if(response.getStatusCode().is2xxSuccessful()) {
				c = response.getBody();
				System.out.println("La cuenta bancaria ya existía previamente");
			}
			
		} catch (HttpClientErrorException e) {
			System.out.println("Error buscando cuenta..." + e.getMessage());
		}
		
		return Optional.ofNullable(c);
	}

}
