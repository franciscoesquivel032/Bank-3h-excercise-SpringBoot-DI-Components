package org.dam2.bancoestudiarspring.controlador;

import java.util.List;

import org.dam2.bancoestudiarspring.model.dto.ClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.CuentaClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.CuentaClienteDTOCompleto;
import org.dam2.bancoestudiarspring.servicio.ICuentaClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banco/cc")
public class CuentaClienteControlador {

	@Autowired private ICuentaClienteServicio servicio;
	
	@GetMapping("/find/{id}")
	public ResponseEntity<CuentaClienteDTO> findById(@PathVariable Integer id){
		return ResponseEntity.of(servicio.findById(id));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody CuentaClienteDTO cc){
		Integer id = servicio.insert(cc);
		
		return id > 0 
				? new ResponseEntity<Integer>(id, HttpStatus.CREATED)
				: ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/findcc/{cliente}/{cuenta}")
	public ResponseEntity<CuentaClienteDTO> findByClienteAndCuenta(@PathVariable String cliente, @PathVariable String cuenta) {
		return ResponseEntity.of(servicio.findByClienteAndCuenta(cliente, cuenta));
	}
	
	@GetMapping("/findclientesbycuenta/{numero}")
	public ResponseEntity<List<ClienteDTO>> findClientesByCuenta(@PathVariable String numero){
		return ResponseEntity.ok(servicio.findClientesByCuenta(numero));
	}
	
	@PostMapping("/insertarcompleto")
	public ResponseEntity<Integer> insertarCompleto(@RequestBody CuentaClienteDTOCompleto cc){
		Integer id = servicio.insertCompleto(cc);
		
		return id > 0 
				? ResponseEntity.status(HttpStatus.CREATED).body(id) 
				: ResponseEntity.badRequest().build();
	}
	
}
