package br.com.api.testepratico.entities.DTOs.client;

import br.com.api.testepratico.entities.models.Client;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientListDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String typeClient;
    private String name;
    private String fantasyName;
    private String document;
    private String cep;
    private String address;
    private String district;
    private String city;
    private String phone;
    private String email;

    public ClientListDTO (Client client){
        this.id = client.getId();
        this.typeClient = client.getTypeClient().name();
        this.name = client.getName();
        this.fantasyName = client.getFantasyName();
        this.document = client.getDocument();
        this.cep = client.getCep();
        this.address = client.getAddress();
        this.district = client.getDistrict();
        this.city = client.getCity();
        this.phone = client.getPhone();
        this.email = client.getEmail();
    }
}
