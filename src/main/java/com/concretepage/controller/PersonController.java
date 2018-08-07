package com.concretepage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.concretepage.entity.Person;
import com.concretepage.service.IPersonService;

@Controller
@Path("/info")
public class PersonController {
	
	@Autowired
	private IPersonService personService;

	@PersonTx
	@GET
	@Path("/person/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonById(@Context HttpServletRequest request, @PathParam("id") Integer id) {
		Person person = personService.getPersonById(id);
		return  Response.status(Status.OK).entity(person).build();
	}

	@GET
	@Path("/person")
	@Produces(MediaType.APPLICATION_JSON)
	@PersonTx
	public Response getAllPersons() {
		List<Person> list = personService.getAllPersons();
		return Response.status(Status.OK).entity(list).build();
	}

	@POST
	@Path("/person")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@PersonTx
	public Response addPerson(@Context HttpServletRequest request, Person person) {
		boolean flag = true;
		List<ValidationErrorMessage> result = new ArrayList<>();
		try {
			flag = personService.addPerson(person);
		} catch (ValidationException ex) {
			flag = false;
			result = ex.getViolations();
		}
		if (flag == false) {
			return Response.status(Status.BAD_REQUEST).entity(result).build();
		}

		return Response.status(Status.OK).entity(person).build();
	}

	@PersonTx
	@RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		personService.updatePerson(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@PersonTx
	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id) {
		personService.deletePerson(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}