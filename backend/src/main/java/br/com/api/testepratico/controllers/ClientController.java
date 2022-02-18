package br.com.api.testepratico.controllers;

import br.com.api.testepratico.entities.DTOs.client.ClientListDTO;
import br.com.api.testepratico.entities.DTOs.company.CompanyCreateDTO;
import br.com.api.testepratico.entities.DTOs.company.CompanyResponseDTO;
import br.com.api.testepratico.entities.DTOs.company.CompanyUpdateDTO;
import br.com.api.testepratico.entities.DTOs.person.PersonCreateDTO;
import br.com.api.testepratico.entities.DTOs.person.PersonResponseDTO;
import br.com.api.testepratico.entities.DTOs.person.PersonUpdateDTO;
import br.com.api.testepratico.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = {"*"},
        maxAge = 3600,
        allowCredentials = "false")
@RestController
@RequestMapping(path = "/api/clients")
public class ClientController {

    @Autowired
    private ClientService service;


    @GetMapping
    public ResponseEntity<List<ClientListDTO>> findAll() {
        List<ClientListDTO> response = service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientListDTO> findById(@PathVariable Long id) {
        ClientListDTO response = service.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/document/{document}")
    public ResponseEntity<ClientListDTO> findByDocument(@PathVariable String document) {
        ClientListDTO response = service.findByDocument(document);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/company")
    public ResponseEntity<CompanyResponseDTO> saveCompany(@RequestBody CompanyCreateDTO companyCreateDTO) {
        CompanyResponseDTO save = service.saveCompany(companyCreateDTO);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PostMapping("/person")
    public ResponseEntity<PersonResponseDTO> savePerson(@RequestBody PersonCreateDTO personCreateDTO) {
        PersonResponseDTO save = service.savePerson(personCreateDTO);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyUpdateDTO companyUpdateDTO) {
        CompanyResponseDTO update = service.updateCompany(id, companyUpdateDTO);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<PersonResponseDTO> updatePerson(@PathVariable Long id, @RequestBody PersonUpdateDTO personUpdateDTO) {
        PersonResponseDTO update = service.updatePerson(id, personUpdateDTO);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
