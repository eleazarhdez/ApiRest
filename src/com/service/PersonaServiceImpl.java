package com.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.dao.PersonaDAOtoHibernateImpl;
import com.dao.PersonaDao;
import com.dao.PersonaEntity;
import com.resources.Error;
import com.resources.Persona;

public class PersonaServiceImpl implements PersonaService {

	PersonaDao personaDao = new PersonaDAOtoHibernateImpl();

	public Response getPersonas() throws JSONException {
		try{
			List<PersonaEntity> personasEntity = personaDao.getPersonas();
			List<Persona> personas = new ArrayList<Persona>();
			for (PersonaEntity personaEntity : personasEntity) {
				Persona persona = new Persona(personaEntity);
				personas.add(persona);
			}
			return Response.status(200).entity(personas).build();
		} catch (Exception ex){
			String mensajeError = "Ha ocurrido un problema y no se ha podido obtener el listado de personas";
			Error error = new Error(404, mensajeError);
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(APPLICATION_JSON_TYPE).build();
		}
	}

	public Response getPersonas(int page, int size) throws JSONException {

		try{
			List<PersonaEntity> personasEntity = personaDao.getPersonas();
			Integer numeroPagina = (personasEntity.size() / size);
			if (size < personasEntity.size()) {
				numeroPagina += 1;
			}
			List<Persona> personas = new ArrayList<Persona>();
	
			for (int i = page * size; i < (page * size + (size)); i++) {
				Persona persona = new Persona(personasEntity.get(i));
				personas.add(persona);
			}
			return Response.status(200).entity(personas).build();
		} catch (Exception ex){
			String mensajeError = "Ha ocurrido al paginar el resultado de personas";
			Error error = new Error(404, mensajeError);
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(APPLICATION_JSON_TYPE).build();
		}
	}

	public Response getPersona(Long id) throws JSONException {

		PersonaEntity personaEntity = personaDao.getPersona(id);
		if (personaEntity != null) {
			Persona persona = new Persona(personaEntity);
			return Response.ok(persona).type(APPLICATION_JSON_TYPE).build();
		}
		String mensajeError = "La persona con el id " + id.toString()
				+ " no fue encontrada en la base de datos. Comprueba que la persona con el id " + id.toString()
				+ " verdaderamente existe";
		Error error = new Error(404, mensajeError);
		return Response.status(Response.Status.NOT_FOUND).entity(error).type(APPLICATION_JSON_TYPE).build();
	}

	public Response createPersona(String nombre) throws JSONException {
		try {
			PersonaEntity personaEntity = personaDao.createPersona(nombre);
			Persona persona = new Persona(personaEntity);
			return Response.status(200).entity(persona).build();
		} catch (Exception e){
			String mensajeError = "No se ha podido crear la persona con el nombre " + nombre;
			Error error = new Error(404, mensajeError);
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(APPLICATION_JSON_TYPE).build();
		}
	}

	public Response updatePersona(Long id, String nombre) throws JSONException {
		
		try {
			PersonaEntity personaEntity = personaDao.updatePersona(id, nombre);
			Persona persona = new Persona(personaEntity);
			return Response.status(200).entity(persona).build();
		} catch (Exception e) {
			String mensajeError = "La persona con el id " + id.toString() + "no se ha podido actualizar o no ha sido encontrada en base de datos";
			Error error = new Error(404, mensajeError);
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(APPLICATION_JSON_TYPE).build();
		}
	}

	public Response createPersona(Persona persona) throws JSONException {
		try{
			PersonaEntity personaEntity = personaDao.createPersona(persona);
			Persona personaCreada = new Persona(personaEntity);
			return Response.status(200).entity(personaCreada).build();
		} catch (Exception ex){
			String mensajeError = "No se ha podido crear la persona";
			Error error = new Error(404, mensajeError);
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(APPLICATION_JSON_TYPE).build();
		}

	}

	public Response deletePersona(Long id) throws JSONException {
		try {
			personaDao.deletePersona(id);
			String mensajeEliminacion = "Persona " + id + " eliminada";
			Error entidadEliminacion = new Error(204, mensajeEliminacion);
			return Response.status(Response.Status.NO_CONTENT).entity(entidadEliminacion).build();
		} catch (Exception ex) {
			String mensajeError = "La persona con el id " + id.toString() + " no se ha podido eliminar o no ha sido encontrada en base de datos";
			Error error = new Error(404, mensajeError);
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(APPLICATION_JSON_TYPE).build();
		}
	}
	
}