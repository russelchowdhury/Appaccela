package russel.accela.appaccela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import russel.accela.appaccela.model.Address;
import russel.accela.appaccela.model.Person;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByIdAndPersonId(Long addressId, Long personId);

    Optional<Address> findByPersonIdAndId(long id, long addressId);

}
