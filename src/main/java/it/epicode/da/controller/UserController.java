package it.epicode.da.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.da.model.Dispositivo;
import it.epicode.da.model.User;
import it.epicode.da.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService uService;

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> allUtenti() {
		List<User> listaUtenti = uService.findAllUtente();
		ResponseEntity<List<User>> resp = new ResponseEntity<List<User>>(listaUtenti, HttpStatus.OK);
		return resp;
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<User> idUtenti(@PathVariable Long id) {
		User u = uService.findUtenteById(id);
		ResponseEntity<User> resp = new ResponseEntity<User>(u, HttpStatus.OK);
		return resp;
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		return new ResponseEntity<String>(uService.removeUtenteById(id), HttpStatus.OK);
	}
	@PutMapping()
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		return new ResponseEntity<User>(uService.updateUser(user), HttpStatus.CREATED);

	}
	@GetMapping("/pag")
	public ResponseEntity<Page<User>> allEdificiPag(Pageable pag) {
		Page<User>listaU = uService.findAllUsersPageble(pag);
		ResponseEntity<Page<User>> resp = new ResponseEntity<Page<User>>(listaU, HttpStatus.OK);
		return resp;
	}
}
