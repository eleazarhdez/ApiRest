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
	private Long dni;

	
	private String nombre;
	
	private String primerApellido;
	
	private String segundoApellido;

	public PersonaEntity() {}

	public PersonaEntity(String nombre) {
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
	
	public PersonaEntity (Persona persona){
		try {
			BeanUtils.copyProperties(this, persona);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}