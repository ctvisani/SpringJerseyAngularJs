package com.concretepage.service;

import java.util.List;

import com.concretepage.controller.ValidationException;
import com.concretepage.entity.Person;

public interface IPersonService {
     List<Person> getAllPersons();
     Person getPersonById(int pid);
     boolean addPerson(Person person) throws ValidationException;
     void updatePerson(Person person);
     void deletePerson(int pid);
}
