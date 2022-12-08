package com.BarInadaptados.BarInadaptados.entities;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idProducto;
	
	@Column(name = "idMenu")
	private Long idMenu;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "precio")
	private Float Precio;
	
	@Column(name = "tiempoCoccion")
	private Time tiempoCoccion;

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() {
		return Precio;
	}

	public void setPrecio(Float precio) {
		Precio = precio;
	}

	public Time getTiempoCoccion() {
		return tiempoCoccion;
	}

	public void setTiempoCoccion(Time tiempoCoccion) {
		this.tiempoCoccion = tiempoCoccion;
	}
	
	


	
	
	
	
}
