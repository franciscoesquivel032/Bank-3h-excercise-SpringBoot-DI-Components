package org.dam2.bancoestudiarspring.controlador;

import org.dam2.bancoestudiarspring.model.dto.ClienteDTO;
import org.dam2.bancoestudiarspring.model.dto.ClienteDTOCompleto;
import org.dam2.bancoestudiarspring.servicio.ClienteServicioImp;
import org.dam2.bancoestudiarspring.servicio.IClienteServicio;
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
@RequestMapping("/banco/cliente")
public class ClienteControlador {
	
	@Autowired IClienteServicio servicio;
	
	@PostMapping("/insertcontfnos")
	public ResponseEntity<String> insertConTfnos(@RequestBody ClienteDTOCompleto cliente){
		return servicio.insertConTfnos(cliente) 
				? new ResponseEntity<String>("Cliente insertado...", HttpStatus.CREATED) 
				: new ResponseEntity<String>("No se ha podido insertar el cliente...", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/find/{nif}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable String nif){
		return ResponseEntity.of(servicio.findById(nif));
	}
	
	@GetMapping("/isadmin/{nif}/{numero}")
	public ResponseEntity<String> isAdmin(@PathVariable String nif,@PathVariable String numero){
		return servicio.isAdmin(nif, numero) 
				? new ResponseEntity<String>("Tiene permisos...", HttpStatus.OK)
				: new ResponseEntity<String>("No tiene permisos....", HttpStatus.FORBIDDEN);
	}
	
	
}
