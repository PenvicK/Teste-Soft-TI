package br.com.api.testepratico.entities.DTOs.person;

import br.com.api.testepratico.entities.models.TypeClient;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonCreateDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private TypeClient typeClient;
    private String name;
    private String cpf;
    private String cep;
    private String address;
    private String district;
    private String city;
    private String phone;
    private String email;
}
