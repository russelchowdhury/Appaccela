package russel.accela.appaccela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import russel.accela.appaccela.exception.ResourceNotFoundException;
import russel.accela.appaccela.model.Address;
import russel.accela.appaccela.model.Person;
import russel.accela.appaccela.repository.AddressRepository;
import russel.accela.appaccela.repository.PersonRepository;

import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public Address addAddress(long personId, Address address) throws ResourceNotFoundException {
        return personRepository.findById(personId).map(person -> {
            address.setPerson(person);
            return addressRepository.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("Person Id " + personId + " not found"));
    }

    @Override
    public Address editAddress(long personId, long addressId, Address addressDetails) throws ResourceNotFoundException {
        if(!personRepository.existsById(personId)) {
            throw new ResourceNotFoundException("Person Id " + personId + " not found");
        }
        return addressRepository.findByPersonIdAndId(personId, addressId).map(address -> {
            address.setStreet(addressDetails.getStreet());
            address.setCity(addressDetails.getCity());
            address.setState(addressDetails.getState());
            address.setPostalCode(addressDetails.getPostalCode());

            return addressRepository.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("Address id " + addressId + " not found for person " + personId));
    }

    @Override
    public String deleteAddress(long personId, long addressId) throws ResourceNotFoundException {
        return addressRepository.findByIdAndPersonId(addressId, personId).map(address -> {
            addressRepository.delete(address);
            return "Address Deleted";
        }).orElseThrow(() -> new ResourceNotFoundException("Address not found with address id " + addressId + " and " +
                "person Id " + personId));
    }

    @Override
    public List<Address> getListOfAddress() {
        List<Address> addresses = addressRepository.findAll();
        /*if (persons.isEmpty()) {
            return (List<Person>) new ResourceNotFoundException("No Person in the database");
        }*/
        return addresses;
    }
}
