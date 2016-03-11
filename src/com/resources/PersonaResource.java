package com.resources;


import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	  
	  @Path("{id}")
	  @GET
	  @Produces({ APPLICATION_JSON, APPLICATION_XML })
	  public Response getPersona(@QueryParam("id") Long id) throws JSONException {

		Persona persona = personaService.getPersona(id);
		  
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
		System.out.println("Persona " + personaEntrada);
		  
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