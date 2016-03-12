package com.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;

import com.resources.Persona;

@Entity
@Table(name="persona")
public class PersonaEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5278377755599211727L;


	@Id
	@GeneratedValue
	private Long id;

	private String dni;
	
	private String nombre;
	
	private String primerApellido;
	
	private String segundoApellido;

	public PersonaEntity() {}
	
	public PersonaEntity (Persona persona){
		try {
			BeanUtils.copyProperties(this, persona);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public PersonaEntity(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	


}