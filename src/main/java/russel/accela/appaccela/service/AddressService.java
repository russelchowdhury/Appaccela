package russel.accela.appaccela.service;

import russel.accela.appaccela.exception.ResourceNotFoundException;
import russel.accela.appaccela.model.Address;
import russel.accela.appaccela.model.Person;

import java.util.List;

public interface AddressService {

    Address addAddress(long personId, Address address) throws ResourceNotFoundException;
    Address editAddress(long personId, long addressId, Address addressDetails) throws ResourceNotFoundException;
    String deleteAddress(long personId, long addressId) throws ResourceNotFoundException;

    List<Address> getListOfAddress();
}
