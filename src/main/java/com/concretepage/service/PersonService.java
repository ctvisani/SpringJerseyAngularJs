package com.concretepage.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.controller.PersonTx;
import com.concretepage.controller.ValidationErrorMessage;
import com.concretepage.controller.ValidationException;
import com.concretepage.dao.IPersonDAO;
import com.concretepage.entity.Person;

@Service
public class PersonService implements IPersonService {
	@Autowired
	private IPersonDAO personDAO;

	@Override
	public Person getPersonById(int pid) {
		Person obj = personDAO.getPersonById(pid);
		return obj;
	}

	@Override
	public List<Person> getAllPersons() {
		return personDAO.getAllPersons();
	}

	@Override
	@PersonTx
	public boolean addPerson(Person person) throws ValidationException {
		if (personDAO.personExists(person.getName(), person.getLocation())) {
			return false;
		} else {
			personDAO.addPerson(person);
			if (person.getName() != null) {
				List<ValidationErrorMessage> validationResult = new LinkedList<ValidationErrorMessage>();
				validationResult.add(
						ValidationErrorMessage.constructErrorMessage("columnKey", "errorMessageKey", "invalidaValue"));
				throw new ValidationException(validationResult);
			}
			
			return true;
		}
	}

	@Override
	public void updatePerson(Person person) {
		personDAO.updatePerson(person);
	}

	@Override
	public void deletePerson(int pid) {
		personDAO.deletePerson(pid);
	}
}
