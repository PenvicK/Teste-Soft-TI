package br.com.api.testepratico.entities.DTOs.company;

import br.com.api.testepratico.entities.models.TypeClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyUpdateDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private String fantasyName;
    private String cnpj;
    private String cep;
    private String address;
    private String district;
    private String city;
    private String phone;
    private String email;
}
