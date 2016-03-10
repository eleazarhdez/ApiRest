package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	
	public void createPersona(Persona persona) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		  
		   EntityTransaction tx = manager.getTransaction();
		   tx.begin();
		   PersonaEntity personaEntity =  new PersonaEntity(persona);
		   try {
		      manager.persist(personaEntity);
		      tx.commit();
		   } catch (Exception e) {
		      e.printStackTrace();
		      tx.rollback();
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
	   
	   Query query = manager.createQuery("Select p From PersonaEntity p where p.id = :id");
	   query.setParameter("id", id);
	   PersonaEntity personaEntity = (PersonaEntity) query.getSingleResult();
	   System.out.println("Persona : " + personaEntity.toString());
	   return personaEntity;
	}
	
	public void updatePersona (Long id, String nombreNuevo){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
		   PersonaEntity personaEntity = manager.find(PersonaEntity.class, id);
		   personaEntity.setNombre(nombreNuevo);
		   manager.persist(personaEntity);
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
		   PersonaEntity personaEntity = manager.find(PersonaEntity.class, id);
		   manager.remove(personaEntity);
		   tx.commit();
		} catch (Exception e){
		   e.printStackTrace();
		   tx.rollback();
		}
	}
}