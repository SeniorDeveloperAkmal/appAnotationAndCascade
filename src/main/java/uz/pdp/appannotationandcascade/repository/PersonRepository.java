package uz.pdp.appannotationandcascade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appannotationandcascade.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
