package com.BarInadaptados.BarInadaptados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BarInadaptados.BarInadaptados.entities.Persona;

public interface PersonaRepository extends JpaRepository <Persona, Long>{
	
}
