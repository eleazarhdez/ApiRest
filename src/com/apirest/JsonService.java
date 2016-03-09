package com.apirest;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.entities.Persona;
import com.entities.PersonaDAO;

 
@Path("/personaService")
public class JsonService {
	
	PersonaDAO personaDao = new PersonaDAO();
 
	  @GET
	  @Produces("application/json")
	  public Response getPersonas() throws JSONException {
 
		List<Persona> personas = personaDao.getPersonas();
		JSONArray personasJson = new JSONArray();
		for (Persona persona: personas){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", persona.getDni());
			jsonObject.put("nombre", persona.getNombre());
			personasJson.put(jsonObject);
		}
 
//		String result =  personasJson.toString();
		return Response.status(200).entity(personasJson).build();
	  }
	  
	  @Path("{id}")
	  @GET
	  @Produces("application/json")
	  public Response getPersona(@PathParam("id") Long id) throws JSONException {

		Persona persona = personaDao.getPersona(id);
		  
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		jsonObject.put("nombre", persona.getNombre());
 
//		String result = jsonObject;
		return Response.status(200).entity(persona).build();
	  }
 
	  @Path("/create")
	  @POST
	  @Produces("application/json")
	  public Response createPersona(@QueryParam("nombre") String nombre) throws JSONException {

		personaDao.createPersona(nombre);
		  
		return Response.status(200).entity("Persona " + nombre + " creada con éxito").build();
	  }
	  

}