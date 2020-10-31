package russel.accela.appaccela;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import russel.accela.appaccela.exception.ResourceNotFoundException;
import russel.accela.appaccela.model.Address;
import russel.accela.appaccela.model.Person;
import russel.accela.appaccela.service.AddressService;
import russel.accela.appaccela.service.PersonService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppaccelaApplicationTests {

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @Test
    public void testAddPerson() {
        Person person1 = new Person(1, "Russel", "Chowdhury");

        personService.addPerson(person1);

        Person person2 = new Person(2, "Niamh", "OConnor");

        personService.addPerson(person2);

        Person testPerson = personService.findPersonByFirstName("Russel");

        Assert.assertEquals(testPerson.getFirstName(), "Russel");
        Assert.assertEquals(testPerson.getLastName(), "Chowdhury");

        List<Person> personList = personService.getListOfPersons();

        Assert.assertEquals(personList.size(), 2);
    }

    @Test
    public void testEditPerson() throws ResourceNotFoundException {

        personService.editPerson(1, new Person("R", "C"));

        Person testPerson = personService.findPersonByFirstName("R");

        Assert.assertEquals(testPerson.getFirstName(), "R");
        Assert.assertEquals(testPerson.getLastName(), "C");
    }

    @Test
    public void testAddAddress() throws ResourceNotFoundException{

        personService.addPerson((new Person(1, "Russel", "Chowdhury")));

        Address address1 = new Address("Summerhill", "Dublin", "Leinster", "D01CF64");

        addressService.addAddress(1, address1);

        Address address2 = new Address("Dorset Street", "Dublin", "Leinster", "D01CF85");

        addressService.addAddress(1, address2);

        List<Address> addressList = addressService.getListOfAddress();

        Assert.assertEquals(addressList.size(), 2);
    }
}
