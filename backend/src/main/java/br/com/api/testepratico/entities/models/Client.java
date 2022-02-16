package br.com.api.testepratico.entities.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialversionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeClient typeClient;
    private String document;
    private String name;
    private String fantasyName;
    private String cep;
    private String address;
    private String district;
    private String city;
    private String phone;
    private String email;
}
