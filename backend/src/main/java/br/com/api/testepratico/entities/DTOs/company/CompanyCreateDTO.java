package br.com.api.testepratico.entities.DTOs.company;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyCreateDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private String fantasyName;
    private String cnpj;
    private String cep;
}
