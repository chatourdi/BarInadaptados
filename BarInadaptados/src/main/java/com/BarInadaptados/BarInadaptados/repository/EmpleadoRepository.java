package com.BarInadaptados.BarInadaptados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BarInadaptados.BarInadaptados.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository <Empleado, Long>{
	
}
