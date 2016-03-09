package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Persona {
	@Id
	@GeneratedValue
	private Long dni;

	
	private String nombre;
	
	private String primerApellido;
	
	private String segundoApellido;

	public Persona() {}

	public Persona(String nombre) {
		this.nombre = nombre;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Persona [DNI=" + dni + ", Nombre=" + nombre +" " +primerApellido + " " + segundoApellido +"]";
	}

}