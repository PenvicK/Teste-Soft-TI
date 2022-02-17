package br.com.api.testepratico.services;

import br.com.api.testepratico.entities.DTOs.client.ClientListDTO;
import br.com.api.testepratico.entities.DTOs.company.CompanyCreateDTO;
import br.com.api.testepratico.entities.DTOs.company.CompanyResponseDTO;
import br.com.api.testepratico.entities.DTOs.company.CompanyUpdateDTO;
import br.com.api.testepratico.entities.DTOs.person.PersonCreateDTO;
import br.com.api.testepratico.entities.DTOs.person.PersonResponseDTO;
import br.com.api.testepratico.entities.DTOs.person.PersonUpdateDTO;
import br.com.api.testepratico.entities.models.Client;
import br.com.api.testepratico.repositories.ClientRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;


    public List<ClientListDTO> findAll() {
        List<Client> response = repository.findAll();
        return response.stream().map(ClientListDTO::new).collect(Collectors.toList());
    }

    public ClientListDTO findById(Long id) {
        Client response =
                repository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found"));
        return new ClientListDTO(response);
    }

    public ClientListDTO findByDocument(String document){
        Client response =
                repository
                        .findByDocument(document)
                        .orElseThrow(
                                () ->
                                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found"));
        return new ClientListDTO(response);
    }

    public CompanyResponseDTO saveCompany(CompanyCreateDTO companyCreateDTO) {
        try {
            Optional<Client> client = repository.findByDocument(companyCreateDTO.getCnpj());

            if(!client.isEmpty()){
                throw new ServiceException("Company already registered");
            }

            Client build =
                    Client.builder()
                            .typeClient(companyCreateDTO.getTypeClient())
                            .name(companyCreateDTO.getName())
                            .fantasyName(companyCreateDTO.getFantasyName())
                            .document(companyCreateDTO.getCnpj())
                            .cep(companyCreateDTO.getCep())
                            .address(companyCreateDTO.getAddress())
                            .district(companyCreateDTO.getDistrict())
                            .city(companyCreateDTO.getCity())
                            .phone(companyCreateDTO.getPhone())
                            .email(companyCreateDTO.getEmail())
                            .build();

            Client save = repository.save(build);
            return new CompanyResponseDTO(save);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public PersonResponseDTO savePerson(PersonCreateDTO personCreateDTO) {
        try {
            Optional<Client> client = repository.findByDocument(personCreateDTO.getCpf());

            if(!client.isEmpty()){
                throw new ServiceException("Company already registered");
            }

            Client build =
                    Client.builder()
                            .typeClient(personCreateDTO.getTypeClient())
                            .name(personCreateDTO.getName())
                            .document(personCreateDTO.getCpf())
                            .cep(personCreateDTO.getCep())
                            .address(personCreateDTO.getAddress())
                            .district(personCreateDTO.getDistrict())
                            .city(personCreateDTO.getCity())
                            .phone(personCreateDTO.getPhone())
                            .email(personCreateDTO.getEmail())
                            .build();

            Client save = repository.save(build);
            return new PersonResponseDTO(save);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public CompanyResponseDTO updateCompany(Long id, CompanyUpdateDTO update) {
        Client updated =
                repository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found"));

        updated.setName(update.getName());
        updated.setFantasyName(update.getFantasyName());
        updated.setDocument(update.getCnpj());
        updated.setCep(update.getCep());
        updated.setAddress(update.getAddress());
        updated.setDistrict(update.getDistrict());
        updated.setCity(update.getCity());
        updated.setPhone(update.getPhone());
        updated.setEmail(update.getEmail());

        Client save = repository.save(updated);
        return new CompanyResponseDTO(save);
    }

    public PersonResponseDTO updatePerson(Long id, PersonUpdateDTO update) {
        Client updated =
                repository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found"));

        updated.setName(update.getName());
        updated.setDocument(update.getCpf());
        updated.setCep(update.getCep());
        updated.setAddress(update.getAddress());
        updated.setDistrict(update.getDistrict());
        updated.setCity(update.getCity());
        updated.setPhone(update.getPhone());
        updated.setEmail(update.getEmail());

        Client save = repository.save(updated);
        return new PersonResponseDTO(save);
    }

    public void delete(Long id) {
        Client product =
                repository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found"));
        repository.delete(product);
    }
}
