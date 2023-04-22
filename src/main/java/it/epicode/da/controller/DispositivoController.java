package it.epicode.da.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.da.model.Dispositivo;
import it.epicode.da.model.User;
import it.epicode.da.service.DispositivoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {
	@Autowired
	DispositivoService dSer;
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Dispositivo>> alldispositivo( ){
		List<Dispositivo> listaD = dSer.findAllDispositivi();
		ResponseEntity<List<Dispositivo>> resp = new ResponseEntity<List<Dispositivo>>(listaD,HttpStatus.OK);
		return resp;
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Dispositivo> idDispositivo(@PathVariable Long id){
		Dispositivo u = dSer.findDispositivoById(id);
		ResponseEntity<Dispositivo> resp = new ResponseEntity<Dispositivo>(u,HttpStatus.OK);
		return resp;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<Dispositivo> createDispositivo(@RequestBody Dispositivo u){
		return new ResponseEntity<Dispositivo>(dSer.saveDispositivo(u),HttpStatus.CREATED);
	}
	@PostMapping("/{id_d}/{id_u}")	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> collegaDispositivoAdUtente(@PathVariable Long id_d,@PathVariable Long id_u){
		return new ResponseEntity<String>(dSer.collegaDispositivoAdUser(id_d, id_u),HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteDispositivo(@PathVariable Long id){
		return new ResponseEntity<String>(dSer.removeDispositivoById(id),HttpStatus.OK);
	}
}
