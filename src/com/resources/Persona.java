package com.resources;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;

import com.dao.PersonaEntity;

@XmlRootElement
public class Persona implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 5278377755599211727L;

		@XmlElement
		private Long id;
		@XmlElement
		private String dni;
		@XmlElement
		private String nombre;
		@XmlElement
		private String primerApellido;
		@XmlElement
		private String segundoApellido;

		public Persona() {}
		
		public Persona(PersonaEntity personaEntity){
			try {
				BeanUtils.copyProperties(this, personaEntity);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		public Persona(String nombre) {
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
