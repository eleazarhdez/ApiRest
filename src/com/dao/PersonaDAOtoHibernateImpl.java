package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.resources.Persona;

public class PersonaDAOtoHibernateImpl implements PersonaDao {

	public PersonaEntity createPersona(String name) throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		PersonaEntity persona = new PersonaEntity(name);
		try {
			manager.persist(persona);
			tx.commit();
			return persona;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception("No se ha podido actualizar la persona.", e);
		}
		
	}

	public PersonaEntity createPersona(Persona persona) throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		PersonaEntity personaEntity = new PersonaEntity(persona);
		try {
			manager.persist(personaEntity);
			tx.commit();
			return personaEntity;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception("No se ha podido crear la persona.", e);
		}
	}

	public List<PersonaEntity> getPersonas() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();

		List<PersonaEntity> personasEntity = manager
				.createQuery("Select persona From PersonaEntity persona", PersonaEntity.class).getResultList();
		return personasEntity;

	}

	public PersonaEntity getPersona(Long id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();

		PersonaEntity personaEntity = manager.find(PersonaEntity.class, id);

		return personaEntity;
	}

	public PersonaEntity updatePersona(Long id, Persona persona) throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
			PersonaEntity personaEntity = manager.find(PersonaEntity.class, id);
			setPersonaEntitiy(personaEntity, persona);
			manager.persist(personaEntity);
			tx.commit();
			return personaEntity;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception("No se ha podido actualizar la persona.", e);	
		}
	}

	private void setPersonaEntitiy(PersonaEntity personaEntity, Persona persona) {

		personaEntity.setDni(persona.getDni() != null ? persona.getDni() : personaEntity.getDni());
		personaEntity.setNombre(persona.getNombre() != null ? persona.getNombre() : personaEntity.getNombre());
		personaEntity.setPrimerApellido(persona.getPrimerApellido() != null ? persona.getPrimerApellido() : personaEntity.getPrimerApellido());
		personaEntity.setSegundoApellido(persona.getSegundoApellido() != null ? persona.getSegundoApellido() : personaEntity.getSegundoApellido());
	}

	public void deletePersona(Long id) throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
			PersonaEntity personaEntity = manager.find(PersonaEntity.class, id);
			manager.remove(personaEntity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception("No se ha podido borrar la persona.", e);
		}
	}
}
