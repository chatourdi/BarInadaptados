package com.BarInadaptados.BarInadaptados.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BarInadaptados.BarInadaptados.entities.Cliente;
import com.BarInadaptados.BarInadaptados.repository.ClienteRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;

	@GetMapping("/")
	public ResponseEntity<List<Cliente>> getclientes() {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();

			clienteRepository.findAll().forEach(clientes::add);

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getcliente(@PathVariable long id) {
		try {
			Optional<Cliente> cliente = this.clienteRepository.findById(id);

			if (cliente.isPresent()) {
				return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/")
	public ResponseEntity<Cliente> createalumno(@RequestBody Cliente cliente) {
		try {
			if (cliente.getId() != null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

			Cliente clienteCreado = clienteRepository.save(cliente);
			return new ResponseEntity<Cliente>(clienteCreado, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updatealumno(@PathVariable long id, @RequestBody Cliente cliente) {
		try {
			Optional<Cliente> clienteEnDBOptional = this.clienteRepository.findById(id);

			if (clienteEnDBOptional.isPresent()) {
				Cliente clienteEnDB = clienteEnDBOptional.get();

				clienteEnDB.setFacebook(cliente.getFacebook());
				clienteEnDB.setInstagram(cliente.getInstagram());
				clienteEnDB.setTwitter(cliente.getTwitter());

				this.clienteRepository.save(clienteEnDB);

				return new ResponseEntity<Cliente>(clienteEnDB, HttpStatus.OK);
			} else {
				return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deletealumno(@PathVariable long id) {
		try {
			this.clienteRepository.deleteById(id);

			return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}