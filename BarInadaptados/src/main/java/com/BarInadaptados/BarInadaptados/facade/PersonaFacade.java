package com.BarInadaptados.BarInadaptados.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BarInadaptados.BarInadaptados.entities.Persona;
import com.BarInadaptados.BarInadaptados.repository.PersonaRepository;

@Component
public class PersonaFacade {

	@Autowired
	PersonaRepository personaRepository;
	
	
	/**
	 * Busca todas las personas de la DB
	 * 
	 * @return lista de personas encontradas
	 */
	public List<Persona> getAllPersonas(){
		List<Persona> personas = new ArrayList<Persona>();
		
		personaRepository.findAll().forEach(personas::add);
		
		return personas;
	}
	
	
	/**
	 * Busca persona en la DB segun id
	 * 
	 * @param id de la persona
	 * @return Persona encontrada, o null si no encuentra nada
	 */
	public Persona getPersonaById(long id){
		Optional <Persona> personaEnDB = personaRepository.findById(id);
		
		if (personaEnDB.isEmpty()) {
			return null;
		} else {
			return personaEnDB.get();
		}
		
	}
	
	
	/**
	 * Crea persona en la DB
	 * 
	 * @param Persona con la informacion para subir sin id
	 * @return personaCreada o null si Persona tenia id
	 */
	public Persona createPersona(Persona persona) {
		Persona personaCreada = new Persona();
		
		if (persona.getId() != null) {
			return null;
		} else {
			personaCreada = personaRepository.save(persona);
			return personaCreada;
		}
	}
	
	
	/**
	 * Actualiza la persona en DB segun id con la info de Persona
	 * 
	 * @param id de la persona a actualizar
	 * @param Persona con la info a actualizar
	 * @return Persona actualizada, o null si no encuentra persona en DB con id
	 */
	public Persona updatePersona(long id, Persona persona) {
		Optional<Persona> personaEnDB = personaRepository.findById(id);
		
		if (personaEnDB.isPresent()) {
			
			Persona personaActualizada = personaEnDB.get();
			
			personaActualizada.setNombre(persona.getNombre());
			personaActualizada.setApellido(persona.getApellido());
			personaActualizada.setUsuario(persona.getUsuario());
			personaActualizada.setContraseña(persona.getContraseña());
			personaActualizada.setMail(persona.getMail());
			personaActualizada.setTelefono(persona.getTelefono());
			
			return personaRepository.save(personaActualizada);
		} else {
			return null;
		}
		
	}
	
	
	/**
	 * Elimina persona en DB segun id
	 * 
	 * @param id de la persona a eliminar
	 */
	public void deletePersona (long id) {
		personaRepository.deleteById(id);
	}
}
