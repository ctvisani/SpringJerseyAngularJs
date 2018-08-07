package com.concretepage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.Person;
import com.concretepage.service.IPersonService;

@Controller
@RequestMapping("/info1")
public class HomeController {

	@Autowired
	private IPersonService personService;

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseEntity<Object> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
		boolean flag = true;
		List<ValidationErrorMessage> result = new ArrayList<>();
		try {
			flag = personService.addPerson(person);
		} catch (ValidationException ex) {
			flag = false;
			result = ex.getViolations();
		}
		if (flag == false) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(person, HttpStatus.OK);
	}
}
