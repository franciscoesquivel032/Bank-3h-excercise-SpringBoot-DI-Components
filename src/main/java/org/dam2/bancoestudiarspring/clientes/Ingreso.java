package org.dam2.bancoestudiarspring.clientes;

import org.dam2.bancoestudiarspring.model.dto.CuentaDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import daw.com.Teclado;


public class Ingreso {

	public static void main(String[] args) {
		
		ClienteUtils.buscarCuenta(Teclado.leerString("numero cuenta"))
				.ifPresentOrElse(
						c -> ingresar(c),
						() -> System.out.println("No se ha encontrado cliente"));; 
	}

	private static Object ingresar(CuentaDTO c) {
		float cantidad = Teclado.leerFloat("cantidad");
		String URL = ClienteUtils.URL + "/cuenta/ingresar/{numero}";
		RestTemplate template = new RestTemplate();
		try {
			
			ResponseEntity<Float> response = template.exchange(URL, HttpMethod.PUT, new HttpEntity<Float>(cantidad), Float.class, c.getNumero());
			if(response.getStatusCode().is2xxSuccessful()) {
				System.out.println("Ingreso realizado...");
				System.out.println("Nuevo saldo = " + response.getBody());
			}
			
		} catch (HttpClientErrorException e) {
			System.out.println("Error realizando ingreso....");
		}
		
		return null;
	}

}
