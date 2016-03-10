package com.service;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.dao.PersonaDAOtoHibernateImpl;
import com.dao.PersonaDao;
import com.dao.PersonaEntity;
import com.resources.Persona;

 

public class PersonaServiceImpl implements PersonaService {
	
	PersonaDao personaDao = new PersonaDAOtoHibernateImpl();

	  public List<Persona> getPersonas() throws JSONException {
 
		List<PersonaEntity> personasEntity = personaDao.getPersonas();
		List<Persona> personas = new ArrayList<Persona>();
		for (PersonaEntity personaEntity: personasEntity){
			Persona persona = new Persona(personaEntity);
			personas.add(persona);
		}
		return personas;
	  }
	  

	  public Persona getPersona(Long id) throws JSONException {

		PersonaEntity personaEntity = personaDao.getPersona(id);
		Persona persona = new Persona(personaEntity);

		return persona;
	  }
 

	  public Persona createPersona(String nombre) throws JSONException {

		PersonaEntity personaEntity = personaDao.createPersona(nombre);
		Persona persona = new Persona(personaEntity);
		  
		return persona;
	  }
	  
	  public void createPersona(Persona persona) throws JSONException {

		personaDao.createPersona(persona);

	  }
	  

}