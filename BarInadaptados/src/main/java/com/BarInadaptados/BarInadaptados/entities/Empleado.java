package com.BarInadaptados.BarInadaptados.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado extends Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "sueldo")
	private float sueldo;
	@Column(name = "horario_trabajo")
	private String horario_trabajo;
	@Column(name = "fecha_ingreso")
	private String fecha_ingreso;
	@Column(name = "funcion")
	private String funcion;
	@Column(name = "id_persona")
	private int id_persona;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public String getHorario_trabajo() {
		return horario_trabajo;
	}

	public void setHorario_trabajo(String horario_trabajo) {
		this.horario_trabajo = horario_trabajo;
	}

	public String getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public int getId_persona() {
		return id_persona;
	}

	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}
}
