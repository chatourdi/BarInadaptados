package com.BarInadaptados.BarInadaptados.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BarInadaptados.BarInadaptados.entities.Empleado;
import com.BarInadaptados.BarInadaptados.entities.Mesa;
import com.BarInadaptados.BarInadaptados.entities.Persona;
import com.BarInadaptados.BarInadaptados.repository.EmpleadoRepository;
import com.BarInadaptados.BarInadaptados.repository.PersonaRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/mesa")
public class EmpleadoController {
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	PersonaRepository personaRepository; 
	
		
	@PostMapping("/")
	public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
		try {
			
			Empleado empleadoCreado = new Empleado();
			
			if (empleado.getId() != null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
				} else {
					
					empleadoCreado.setNombre(empleado.getNombre());
					empleadoCreado.setApellido(empleado.getApellido());
					empleadoCreado.setContraseña(empleado.getContraseña());
					empleadoCreado.setUsuario(empleado.getUsuario());
					empleadoCreado.setDni(empleado.getDni());
					empleadoCreado.setMail(empleado.getMail());
					empleadoCreado.setTelefono(empleado.getTelefono());
					empleadoCreado.setRol("Empleado");
					
					empleadoCreado = personaRepository.save(empleadoCreado);	
				
					empleadoCreado = empleadoRepository.save(empleado);
			}	
			
			if (empleadoCreado == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Empleado>(empleadoCreado, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//=========================================================
	// Prgunta: Como hacer para crear, actualizar y eliminar en las tablas de persona y empleado usando solo el repostory de empleado
	//========================================================
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Empleado> updateEmpleado(@PathVariable long id, @RequestBody Empleado empleado) {
		try {
			Optional<Empleado> empleadoEnDB = empleadoRepository.findById(id);
			
			if (mesaEnDB.isPresent()) {
				
				Mesa mesaActualizada = mesaEnDB.get();
				
				mesaActualizada.setId(mesa.getId());
				mesaActualizada.setNumeroMesa(mesa.getNumeroMesa());
				mesaActualizada.setCapacidad(mesa.getCapacidad());
				mesaActualizada.setCaracteristicas(mesa.getCaracteristicas());
				
				mesaActualizada = mesaRepository.save(mesaActualizada);
				return new ResponseEntity<Mesa>(mesaActualizada, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
				
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Mesa> deleteMesa(@PathVariable long id) {
		try {
			mesaRepository.deleteById(id);
			
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
