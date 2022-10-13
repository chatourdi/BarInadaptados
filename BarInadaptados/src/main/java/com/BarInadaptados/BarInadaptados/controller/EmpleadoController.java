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
import com.BarInadaptados.BarInadaptados.entities.Empleado;
import com.BarInadaptados.BarInadaptados.repository.EmpleadoRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

	@Autowired
	EmpleadoRepository empleadoRepository;

	@GetMapping("/")
	public ResponseEntity<List<Empleado>> getempleados() {
		try {
			List<Empleado> empleados = new ArrayList<Empleado>();

			empleadoRepository.findAll().forEach(empleados::add);

			return new ResponseEntity<>(empleados, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empleado> getempleado(@PathVariable long id) {
		try {
			Optional<Empleado> empleado = this.empleadoRepository.findById(id);

			if (empleado.isPresent()) {
				return new ResponseEntity<Empleado>(empleado.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Empleado>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/")
	public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
		try {
			if (empleado.getId() != null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

			Empleado empleadoCreado = empleadoRepository.save(empleado);
			return new ResponseEntity<Empleado>(empleadoCreado, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Empleado> updateEmpleado(@PathVariable long id, @RequestBody Empleado empleado) {
		try {
			Optional<Empleado> empleadoEnDBOptional = this.empleadoRepository.findById(id);

			if (empleadoEnDBOptional.isPresent()) {
				Empleado empleadoEnDB = empleadoEnDBOptional.get();

				empleadoEnDB.setSueldo(empleado.getSueldo());
				empleadoEnDB.setHorario_trabajo(empleado.getHorario_trabajo());
				empleadoEnDB.setFecha_ingreso(empleado.getFecha_ingreso());
				empleadoEnDB.setFuncion(empleado.getFuncion());
				empleadoEnDB.setId_persona(empleado.getId_persona());

				this.empleadoRepository.save(empleadoEnDB);

				return new ResponseEntity<Empleado>(empleadoEnDB, HttpStatus.OK);
			} else {
				return new ResponseEntity<Empleado>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deletealumno(@PathVariable long id) {
		try {
			this.empleadoRepository.deleteById(id);

			return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}