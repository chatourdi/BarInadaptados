package com.BarInadaptados.BarInadaptados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BarInadaptados.BarInadaptados.entities.Producto;

public interface ProductoRepository extends JpaRepository <Producto, Long>{
	
}
