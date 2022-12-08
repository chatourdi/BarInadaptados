package com.BarInadaptados.BarInadaptados.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.BarInadaptados.BarInadaptados.entities.Menu;
import com.BarInadaptados.BarInadaptados.entities.Mesa;
import com.BarInadaptados.BarInadaptados.entities.Producto;
import com.BarInadaptados.BarInadaptados.repository.MenuRepository;
import com.BarInadaptados.BarInadaptados.repository.ProductoRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/mesa")
public class MenuController {
	
	@Autowired
	MenuRepository menuRepository;
	ProductoRepository productoRepository;
	
		
	@PostMapping("/")
	public ResponseEntity<Menu> createMesa(@RequestBody Menu menu) {
		try {
			
			Menu menuCreado = new Menu();
			
			if (menu.getIdMenu() != null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
				} else {
					
					menuCreado.setNombreMenu(menu.getNombreMenu());
			
					menuCreado = menuRepository.save(menuCreado);
					
					Producto[] productos= menu.getProductos();
					List<Producto> listaProductosCreados = new ArrayList<Producto>();
					Producto productoCreado = new Producto();
					
					for (int i=0; i<productos.length; i++) {
						
						productoCreado.setNombre(productos[i].getNombre());
						productoCreado.setPrecio(productos[i].getPrecio());
						productoCreado.setTiempoCoccion(productos[i].getTiempoCoccion());
						productoCreado.setIdMenu(menuCreado.getIdMenu());
						
						listaProductosCreados.add(productoRepository.save(productoCreado));
					}	
					
					Producto[] productosCreados = listaProductosCreados.toArray(new Producto[0]);
					
					if (productosCreados != null)
						menuCreado.setProductos(productosCreados);
				
			}	
			
			if (menuCreado == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Menu>(menuCreado, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//========== Falta update y delete ====================
	
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
