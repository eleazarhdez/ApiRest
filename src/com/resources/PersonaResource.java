package com.resources;


import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.service.PersonaService;
import com.service.PersonaServiceImpl;

 
@Path("/persona")
public class PersonaResource {
	
	PersonaService personaService = new PersonaServiceImpl();
 
	  @GET
	  @Produces("application/json")
	  public Response getPersonas() throws JSONException {
 
		List<Persona> personas = personaService.getPersonas();

		return Response.status(200).entity(personas).build();
	  }
	  
	  @GET
	  @Path("{id}")
	  @Produces(APPLICATION_JSON)
	  public Response getPersona(@PathParam("id") Long id) throws JSONException {

		Persona persona = personaService.getPersona(id);
		if (persona == null){
			String mensajeError = "La persona con el id " + id.toString() + " no fue encontrada en la base de datos. Comprueba que la persona con el id " 
		                          + id.toString() + " verdaderamente existe";
			Error error = new Error(404, mensajeError);
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(APPLICATION_JSON_TYPE).build();
		}	  
		return Response.ok(persona).type(APPLICATION_JSON_TYPE).build();

	  }
	  
//	  @Path("/create")
//	  @POST
//	  @Consumes(APPLICATION_JSON)
//	  @Produces("application/json")
//	  public Response createPersona(@QueryParam("nombre") String nombre) throws JSONException {
//
//		Persona persona = personaService.createPersona(nombre);
//		  
//		return Response.status(200).entity(persona).build();
//	  }
 
	  @Path("/create")
	  @POST
	  @Consumes(APPLICATION_JSON)
	  @Produces("application/json")
	  public Response createPersona(Persona personaEntrada) throws JSONException {

		personaService.createPersona(personaEntrada);
		  
		return Response.status(200).entity(personaEntrada).build();
	  }
	  
		@DELETE
		@Path("/delete/{id}")
		@Produces(APPLICATION_JSON)
		public Response deletePodcastById(@PathParam("id") Long id) {
			
			personaService.deletePersona(id);
			
			String mensajeEliminacion = "Persona " + id + " eliminada";
			Error entidadEliminacion = new Error(204, mensajeEliminacion);
			
			return Response.status(Response.Status.NO_CONTENT).entity(entidadEliminacion).build();
		}

}