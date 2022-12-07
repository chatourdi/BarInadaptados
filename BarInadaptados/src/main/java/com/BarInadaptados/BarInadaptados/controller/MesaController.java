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

import com.BarInadaptados.BarInadaptados.entities.Mesa;
import com.BarInadaptados.BarInadaptados.repository.MesaRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/mesa")
public class MesaController {
	
	@Autowired
	MesaRepository mesaRepository;
	
		
	@PostMapping("/")
	public ResponseEntity<Mesa> createMesa(@RequestBody Mesa mesa) {
		try {
			
			Mesa mesaCreada = new Mesa();
			
			if (mesa.getId() != null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
				} else {
				
				mesaCreada = mesaRepository.save(mesa);
			}	
			
			if (mesaCreada == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Mesa>(mesaCreada, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Mesa> updateMesa(@PathVariable long id, @RequestBody Mesa mesa) {
		try {
			Optional<Mesa> mesaEnDB = mesaRepository.findById(id);
			
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
