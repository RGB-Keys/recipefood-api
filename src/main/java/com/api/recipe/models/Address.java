package com.api.recipe.models;

import com.api.recipe.dtos.address.AddressData;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {
    private String logradouro;
    private String cidade;
    private String uf;
    private String numero;
    private String cep;
    private String complemento;

    public Address(AddressData data) {
        this.logradouro = data.logradouro();
        this.cidade = data.cidade();
        this.uf = data.uf();
        this.numero = data.numero();
        this.cep = data.cep();
        this.complemento = data.complemento();
    }
}
