package com.service;

import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.resources.Persona;

public interface PersonaService {
	
   public Response getPersonas() throws JSONException;
   
   public Response getPersonas(int page, int size) throws JSONException;
	  
   public Response getPersona(Long id) throws JSONException;

   public Response createPersona(String nombre) throws JSONException;
   
   public Response createPersona(Persona persona) throws JSONException;
   
   public Response updatePersona(Long id, Persona persona) throws JSONException;
   
   public Response deletePersona (Long id) throws JSONException;
   
}
