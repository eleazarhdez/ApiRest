package com.dao;

import java.util.List;

import com.resources.Persona;

public interface PersonaDao {
	
	public PersonaEntity createPersona(String name); 
	
	public void createPersona(Persona persona); 

	public List<PersonaEntity> getPersonas(); 
	
	public PersonaEntity getPersona(Long id); 
	
	public void updatePersona (Long id, String nombreNuevo);
	
	public void deletePersona (Long id);
}
