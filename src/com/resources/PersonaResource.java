package com.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Path("/all")
	@Produces(APPLICATION_JSON)
	public Response getPersonas() throws JSONException {

		Response response = personaService.getPersonas();

		return response;
	}

	@GET
	@Produces(APPLICATION_JSON)
	public Response getPersonas(@QueryParam("page") int page, @QueryParam("size") int size) throws JSONException {

		Response response = personaService.getPersonas(page, size);

		return response;
	}

	@GET
	@Path("{id}")
	@Produces(APPLICATION_JSON)
	public Response getPersona(@PathParam("id") Long id) throws JSONException {

		Response response = personaService.getPersona(id);

		return response;

	}


	@Path("/create")
	@POST
	@Consumes(APPLICATION_JSON)
	@Produces("application/json")
	public Response createPersona(Persona personaEntrada) throws JSONException {

		Response response = personaService.createPersona(personaEntrada);

		return response;
	}

	@Path("/update")
	@POST
	@Consumes(APPLICATION_JSON)
	@Produces("application/json")
	public Response updatePersona(@QueryParam("id") Long id, Persona personaEntrada) throws JSONException {

		Response response = personaService.updatePersona(id, personaEntrada);

		return response;
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(APPLICATION_JSON)
	public Response deletePodcastById(@PathParam("id") Long id) {

		Response response = personaService.deletePersona(id);

		return response;
	}

}