package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.resources.Persona;

public class PersonaDAOtoHibernateImpl implements PersonaDao{

	public PersonaEntity createPersona(String name) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		  
		   EntityTransaction tx = manager.getTransaction();
		   tx.begin();
		   PersonaEntity persona = new PersonaEntity(name);
		   try {
		      manager.persist(persona);
		      tx.commit();
		   } catch (Exception e) {
		      e.printStackTrace();
		      tx.rollback();
		   }
	   return persona;
	}
	
	public PersonaEntity createPersona(Persona persona) throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		  
		   EntityTransaction tx = manager.getTransaction();
		   tx.begin();
		   PersonaEntity personaEntity =  new PersonaEntity(persona);
		   try {
		      manager.persist(personaEntity);
		      tx.commit();
		      return personaEntity;
		   } catch (Exception e) {
		      e.printStackTrace();
		      tx.rollback();
		      return null;
		   }
	}
	
	
	public List<PersonaEntity> getPersonas() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();

	   List<PersonaEntity> personasEntity = manager.createQuery("Select persona From PersonaEntity persona", PersonaEntity.class).getResultList();
	   return personasEntity;

	}
	
	public PersonaEntity getPersona(Long id) {
	   EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
	   EntityManager manager = factory.createEntityManager();
	   
	   PersonaEntity personaEntity = manager.find(PersonaEntity.class,id);

	   return personaEntity;
	}
	
	public PersonaEntity updatePersona (Long id, String nombreNuevo) throws Exception{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
		   PersonaEntity personaEntity = manager.find(PersonaEntity.class, id);
		   personaEntity.setNombre(nombreNuevo);
		   manager.persist(personaEntity);
		   tx.commit();
		   return personaEntity;
		} catch (Exception e) {
		   e.printStackTrace();
		   tx.rollback();
		   return null;
		}	
	}
	
	public void deletePersona (Long id) throws Exception{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		      
		try {
		   PersonaEntity personaEntity = manager.find(PersonaEntity.class, id);
		   manager.remove(personaEntity);
		   tx.commit();
		} catch (Exception e){
		   e.printStackTrace();
		   tx.rollback();
		}
	}
}
