package uz.pdp.appannotationandcascade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appannotationandcascade.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
