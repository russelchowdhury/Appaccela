package russel.accela.appaccela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import russel.accela.appaccela.exception.ResourceNotFoundException;
import russel.accela.appaccela.model.Person;
import russel.accela.appaccela.repository.PersonRepository;

import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public void addPerson(Person newPerson) {
        personRepository.save(newPerson);
    }

    @Override
    public void editPerson(long personId, Person personDetails) throws ResourceNotFoundException{
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id : " + personId));

        // if both need edit
        if(!personDetails.getFirstName().equals("na") && !personDetails.getLastName().equals("na")){
            person.setFirstName(personDetails.getFirstName());
            person.setLastName(personDetails.getLastName());
            personRepository.save(person);
        }

        // if there is no edit at all
        if(personDetails.getLastName().equals("na") && personDetails.getLastName().equals("na")){

        }

        // if only lastName need edit
        if (personDetails.getFirstName().equals("na") && !personDetails.getLastName().equals("na")){
            person.setFirstName(person.getFirstName());
            person.setLastName(personDetails.getLastName());
            personRepository.save(person);
        }

        // if only firstName need edit
        if (!personDetails.getFirstName().equals("na") && personDetails.getLastName().equals("na")){
            person.setFirstName(personDetails.getFirstName());
            person.setLastName(person.getLastName());
            personRepository.save(person);
        }
    }

    @Override
    public void deletePerson(long personId) throws ResourceNotFoundException{
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to delete. Person with id "  + personId + " not " +
                        "found."));

        personRepository.delete(person);
    }

    @Override
    public long getTotalNumberOfPersons() {
        return personRepository.count();
    }

    @Override
    public List<Person> getListOfPersons() {
        List<Person> persons = personRepository.findAll();
        /*if (persons.isEmpty()) {
            return (List<Person>) new ResourceNotFoundException("No Person in the database");
        }*/
        return persons;
    }

    @Override
    public Person findPersonByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }
}
