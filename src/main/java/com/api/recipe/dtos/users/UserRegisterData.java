package com.api.recipe.dtos.users;

import com.api.recipe.dtos.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record UserRegisterData(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @CPF String cpf,
        @NotBlank String telefone,
        @NotNull UserType categoria,
        @NotNull @Valid AddressData endereco
        ) {
}
