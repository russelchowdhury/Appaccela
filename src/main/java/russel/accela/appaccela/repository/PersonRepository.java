package russel.accela.appaccela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import russel.accela.appaccela.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String name);
}
