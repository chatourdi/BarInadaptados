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

import com.BarInadaptados.BarInadaptados.entities.Producto;
import com.BarInadaptados.BarInadaptados.repository.ProductoRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/mesa")
public class ProductoController {
	
	@Autowired
	ProductoRepository productoRepository;
	
		
	@PostMapping("/")
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		try {
			
			Producto productoCreado = new Producto();
			
			if (producto.getIdProducto() != null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
				} else {
				
				productoCreado = productoRepository.save(producto);
			}	
			
			if (productoCreado == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Producto>(productoCreado, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable long id, @RequestBody Producto producto) {
		try {
			Optional<Producto> productoEnDB = productoRepository.findById(id);
			
			if (productoEnDB.isPresent()) {
				
				Producto productoActualizado = productoEnDB.get();
				
				productoActualizado.setIdProducto(producto.getIdProducto());
				productoActualizado.setIdMenu(producto.getIdMenu());
				productoActualizado.setNombre(producto.getNombre());
				productoActualizado.setPrecio(producto.getPrecio());
				productoActualizado.setTiempoCoccion(producto.getTiempoCoccion());
				
				productoActualizado = productoRepository.save(productoActualizado);
				return new ResponseEntity<Producto>(productoActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
				
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Producto> deleteProducto(@PathVariable long id) {
		try {
			productoRepository.deleteById(id);
			
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
