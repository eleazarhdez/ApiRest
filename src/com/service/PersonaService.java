package com.service;

import java.util.List;

import org.json.JSONException;

import com.resources.Persona;

public interface PersonaService {
	
   public List<Persona> getPersonas() throws JSONException;
	  
   public Persona getPersona(Long id) throws JSONException;

   public Persona createPersona(String nombre) throws JSONException;
   
   public void createPersona(Persona persona) throws JSONException;
   
}
