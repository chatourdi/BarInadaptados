package com.BarInadaptados.BarInadaptados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BarInadaptados.BarInadaptados.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}

