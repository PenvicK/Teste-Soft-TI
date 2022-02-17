package br.com.api.testepratico.repositories;

import br.com.api.testepratico.entities.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByDocument(String document);
}
