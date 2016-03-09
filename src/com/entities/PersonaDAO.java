package com.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PersonaDAO {

	public void createPersona(String name) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		  
		   EntityTransaction tx = manager.getTransaction();
		   tx.begin();

		   try {
		      manager.persist(new Persona(name));
		      tx.commit();
		   } catch (Exception e) {
		      e.printStackTrace();
		      tx.rollback();
		   }
	}
	
	public List<Persona> getPersonas() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();

	   List<Persona> personas = manager.createQuery("Select persona From Persona persona", Persona.class).getResultList();
	   return personas;

	}
	
	public Persona getPersona(Long id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
	   System.out.println("Lista Personas :");
	   
	   Query query = manager.createQuery("Select persona From Persona persona where persona.id = :id");
	   query.setParameter("id", id);
	   Persona persona = (Persona) query.getSingleResult();
	   System.out.println("Persona : " + persona.toString());
	   return persona;
	}
	
	public void updatePersona (Long id, String nombreNuevo){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
		   Persona persona = manager.find(Persona.class, id);
		   persona.setNombre(nombreNuevo);
		   manager.persist(persona);
		   tx.commit();
		} catch (Exception e) {
		   e.printStackTrace();
		   tx.rollback();
		}
	}
	
	public void deletePersona (Integer id){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		      
		try {
		   Persona persona = manager.find(Persona.class, id);
		   manager.remove(persona);
		   tx.commit();
		} catch (Exception e){
		   e.printStackTrace();
		   tx.rollback();
		}
	}
}
