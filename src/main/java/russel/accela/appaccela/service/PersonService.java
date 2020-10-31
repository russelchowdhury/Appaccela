package russel.accela.appaccela.service;

import russel.accela.appaccela.exception.ResourceNotFoundException;
import russel.accela.appaccela.model.Person;

import java.util.List;

public interface PersonService {

    void addPerson(Person newPerson);
    void editPerson(long personId, Person personDetails) throws ResourceNotFoundException;
    void deletePerson(long personId) throws ResourceNotFoundException;
    long getTotalNumberOfPersons();
    List<Person> getListOfPersons();

    // Test Function
    Person findPersonByFirstName(String firstName);
}
