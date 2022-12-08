package com.BarInadaptados.BarInadaptados.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idMenu;
	
	@Column(name = "nombreMenu")
	private String nombreMenu;

	@Column(name = "productos")
	private Producto[] productos;

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public String getNombreMenu() {
		return nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public Producto[] getProductos() {
		return productos;
	}

	public void setProductos(Producto[] productos) {
		this.productos = productos;
	}
	
	
	


	
	
	
	
}
