package org.dam2.bancoestudiarspring.controlador;

import org.dam2.bancoestudiarspring.model.dto.CuentaDTO;
import org.dam2.bancoestudiarspring.servicio.ICuentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banco/cuenta")
public class CuentaControlador {
	
	@Autowired private ICuentaServicio servicio;
	
	@GetMapping("/find/{numero}")
	public ResponseEntity<CuentaDTO> findById(String numero){
		return ResponseEntity.of(servicio.findById(numero));
	}
	
	@PutMapping("/ingresar/{numero}")
	public ResponseEntity<Float> ingresar(@PathVariable String numero, @RequestBody float cantidad){
		float response = servicio.ingresar(numero, cantidad);
		
		return response > 0 
				? ResponseEntity.ok(response)
				: ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/retirar/{numero}")
	public ResponseEntity<String> retirar(@PathVariable String numero, @RequestBody float cantidad){
		float result;
		HttpStatus status = HttpStatus.OK;
		String body = "Retirada realizada con éxito... Nuevo saldo :";
		
		try {
			
			result = servicio.retirar(numero, cantidad);
			body += result;
			
		} catch (IllegalArgumentException | IllegalStateException e) {
			body = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<String>(body, status);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<String> crearCuenta(@RequestBody CuentaDTO cuenta){
		return servicio.crearCuenta(cuenta)
				? ResponseEntity.ok("Cuenta creada con éxito...")
				: ResponseEntity.badRequest().body("No se ha podido crear la cuenta....");
	}
	
}
