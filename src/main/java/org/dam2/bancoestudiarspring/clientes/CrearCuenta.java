package org.dam2.bancoestudiarspring.clientes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dam2.bancoestudiarspring.model.dto.ClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.CuentaClienteDTOCompleto;
import org.dam2.bancoestudiarspring.model.dto.CuentaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import daw.com.Teclado;

public class CrearCuenta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String numCuenta;
		String nif;
		ClienteDTO cliente;
		CuentaDTO cuenta;
		boolean admin;
		List<CuentaClienteDTOCompleto> ccs = new ArrayList<>();
		List<ClienteDTO> clientes = new ArrayList<>();
		CuentaClienteDTOCompleto cc;
		
		
		do {
			nif = Teclado.leerString("Nif");
			cliente = ClienteUtils.buscarCliente(nif).orElse(crearCliente(nif));
			admin = Teclado.leerString("admin?").equalsIgnoreCase("s");
			clientes.add(cliente);
			cc = CuentaClienteDTOCompleto.builder().cliente(cliente).admin(admin).build();
			ccs.add(cc);
		}while(Teclado.leerString("Otro ?").equalsIgnoreCase("s"));
		
		numCuenta = Teclado.leerString("Num cuenta");
		cuenta = ClienteUtils.buscarCuenta(numCuenta).orElse(crearCuenta(numCuenta));
		
		ccs.forEach(ccl -> {
			ccl.setCuenta(cuenta);
			crearCC(ccl);
		});
	}

	private static void crearCC(CuentaClienteDTOCompleto ccl) {
		String URL = ClienteUtils.URL + "/cc/insertarcompleto";
		RestTemplate template = new RestTemplate();
		
		try {
			
			ResponseEntity<Integer> response = template.postForEntity(URL, ccl, Integer.class);
			if(response.getStatusCode().is2xxSuccessful()) {
				System.out.println("Cuenta creada para cliente " + ccl.getCliente());
			}
			
		} catch (HttpClientErrorException e) {
			System.out.println("Error creado cuenta cliente..." + e.getMessage());
		}
		
	}

	private static CuentaDTO crearCuenta(String numCuenta) {
		CuentaDTO c = CuentaDTO.builder().numero(numCuenta).saldo(Teclado.leerFloat("Saldo")).build();
		return c;
	}



	
	private static ClienteDTO crearCliente(String nif) {
		ClienteDTO c = ClienteDTO.builder()
				.nif(nif)
				.nombre(Teclado.leerString("Nombre"))
				.maxAvales(Teclado.leerFloat("Max avales"))
				.build();
		
		return c;
	}

}
