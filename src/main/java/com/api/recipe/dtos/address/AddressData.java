package com.api.recipe.dtos.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
        @NotBlank String logradouro,
        @NotBlank String cidade,
        @NotBlank @Pattern(regexp = "\\d{8}") String cep,
        @NotBlank String uf,
        String numero,
        String complemento
) {
}
