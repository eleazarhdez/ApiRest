package test.java;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import org.json.JSONException;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;



public class restTest extends JerseyTest {
	

	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	@Test
	public void testCrearObtener() throws JSONException, URISyntaxException {
		WebResource webResource = client().resource("http://localhost:8080/");
		webResource.type("application/json").accept("application/json").header("Content-Type","application/json").get(ClientResponse.class);
		
		PersonaTest personaTestToCreate = buildPersonaTest();
		PersonaTest personaTestCreated = webResource.path("/ApiREST/persona/create").header("Content-Type","application/json").post(PersonaTest.class, personaTestToCreate);
		
		PersonaTest personaTestToGet = webResource.path("/ApiREST/persona/"+ personaTestCreated.id).get(PersonaTest.class);
		assertEquals(personaTestCreated.id, personaTestToGet.id);
		assertEquals(personaTestCreated.nombre, personaTestToGet.nombre);
		assertEquals(personaTestCreated.primerApellido, personaTestToGet.primerApellido);
		assertEquals(personaTestCreated.segundoApellido, personaTestToGet.segundoApellido);
	}
	
	@Test
	public void testCrearEliminar() throws JSONException, URISyntaxException {
		WebResource webResource = client().resource("http://localhost:8080/");
		webResource.type("application/json").accept("application/json").header("Content-Type","application/json").get(ClientResponse.class);
		
		PersonaTest personaTestToCreate = buildPersonaTest();
		PersonaTest personaTestCreated = webResource.path("/ApiREST/persona/create").header("Content-Type","application/json").post(PersonaTest.class, personaTestToCreate);
		
		
		ClientResponse response = webResource.path("/ApiREST/persona/delete/"+ personaTestCreated.id).delete(ClientResponse.class, personaTestCreated);
		
		
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testCrearActualizar() throws JSONException, URISyntaxException {
		WebResource webResource = client().resource("http://localhost:8080/");
		webResource.type("application/json").accept("application/json").header("Content-Type","application/json").get(ClientResponse.class);
		
		PersonaTest personaTestToCreate = buildPersonaTest();
		PersonaTest personaTestCreated = webResource.path("/ApiREST/persona/create").header("Content-Type","application/json").post(PersonaTest.class, personaTestToCreate);
		
		PersonaTest personaTesToModify = buildPersonaModified();
		PersonaTest personaModified = webResource.path("/ApiREST/persona/update").
				queryParam("id", personaTestCreated.id.toString()).
				header("Content-Type","application/json").
				post(PersonaTest.class, personaTesToModify);
		
		PersonaTest personaTestToGet = webResource.path("/ApiREST/persona/"+ personaTestCreated.id).get(PersonaTest.class);
		
		
		assertEquals(personaTestCreated.id, personaTestToGet.id);
		assertNotEquals(personaTestCreated.nombre, personaTestToGet.nombre);
		assertNotEquals(personaTestCreated.dni, personaTestToGet.dni);
		assertEquals(personaTestCreated.primerApellido, personaTestToGet.primerApellido);
		assertEquals(personaTestCreated.segundoApellido, personaTestToGet.segundoApellido);
	}
	
	private PersonaTest buildPersonaTest() {
		PersonaTest personaTest = new PersonaTest();
		personaTest.dni = "11111111";
		personaTest.nombre= "NombrePrueba";
		personaTest.primerApellido = "Pérez";
		personaTest.segundoApellido = "González";
		return personaTest;
	}
	
	private PersonaTest buildPersonaModified() {
		PersonaTest personaTest = new PersonaTest();
		personaTest.dni = "22222222";
		personaTest.nombre= "NombreModificado";
		return personaTest;
	}
	  

}
