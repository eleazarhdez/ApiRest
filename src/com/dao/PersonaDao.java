package com.dao;

import java.util.List;

import com.resources.Persona;

public interface PersonaDao {
	
	public PersonaEntity createPersona(String name) throws Exception; 
	
	public PersonaEntity createPersona(Persona persona) throws Exception; 

	public List<PersonaEntity> getPersonas(); 
	
	public PersonaEntity getPersona(Long id); 
	
	public PersonaEntity updatePersona (Long id, String nombreNuevo) throws Exception;
	
	public void deletePersona (Long id) throws Exception;
}
