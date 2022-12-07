package com.BarInadaptados.BarInadaptados.controller;

import java.util.List;

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

import com.BarInadaptados.BarInadaptados.entities.Persona;
import com.BarInadaptados.BarInadaptados.facade.PersonaFacade;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/personas")
public class PersonaController {
	
	@Autowired
	PersonaFacade personaFacade;
	
	@GetMapping("/")
	public ResponseEntity<List<Persona>> getPersonas() {
		try {
			List<Persona> personas = this.personaFacade.getAllPersonas();
			
			return new ResponseEntity<>(personas , HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> getPersona(@PathVariable long id) {
		try {
			Persona persona = this.personaFacade.getPersonaById(id);
			
			if (persona != null) {
				return new ResponseEntity<Persona>(persona, HttpStatus.OK);
			} else {
				return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND); 
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
		try {
			Persona personaCreada = personaFacade.createPersona(persona);
			
			if (personaCreada == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Persona>(personaCreada, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Persona> updatePersona(@PathVariable long id, @RequestBody Persona persona) {
		try {
			Persona personaActualizada = personaFacade.updatePersona(id, persona);
			
			if (personaActualizada == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Persona>(personaActualizada, HttpStatus.OK);
			}
				
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Persona> deletePersona(@PathVariable long id) {
		try {
			personaFacade.deletePersona(id);
			
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
