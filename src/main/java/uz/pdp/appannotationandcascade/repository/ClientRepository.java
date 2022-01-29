package uz.pdp.appannotationandcascade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appannotationandcascade.entity.Client;

@RepositoryRestResource(path = "client")
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
