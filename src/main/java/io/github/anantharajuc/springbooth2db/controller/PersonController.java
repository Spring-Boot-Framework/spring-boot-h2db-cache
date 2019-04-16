package io.github.anantharajuc.springbooth2db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.springbooth2db.model.Person;
import io.github.anantharajuc.springbooth2db.service.PersonServiceImpl;

@RestController
@RequestMapping("/Persons")
public class PersonController 
{
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	/**
     * Retrieve all persons
     * 
     * @return List<Person> with all persons
     */
	@GetMapping(path="/all", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
    private List<Person> getAllPersons() 
	{
        return personServiceImpl.getAllPersons();
    }
	
	/**
     * Get a Person by id.
     * 
     * @param id. The persons id         
     * @return the person
     */
	@GetMapping(value = "/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
    private Person getPerson(@PathVariable("id") Long id) 
	{
        return personServiceImpl.getPersonById(id);
    }
	
	/**
     * Create/Update a person. 
     * Returned person will have the auto-generated id if its newly created.
     * 
     * @param person. The person to create
     * @return the created person
     */
	@PostMapping(path="/persons", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
    private Person savePerson(@RequestBody Person person) 
	{
        personServiceImpl.saveOrUpdate(person);
        return person; 
    }
	
	/**
     * Delete Person
     * 
     * @param id. The persons id.
     * @throws ResourceNotFoundException if the person is not found.
     */
	@DeleteMapping("/person/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePerson(@PathVariable("id") Long id) 
	{
		try 
		{
			personServiceImpl.delete(id);
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			throw new ResourceNotFoundException("Person Resource not found");
		}
    }
} 
