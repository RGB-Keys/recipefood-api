package com.api.recipe.dtos.users;

import com.api.recipe.models.Address;

public record UserUpdateData(
        Long id,
        String nome,
        String email,
        String telefone,
        UserType categoria,
        Address endereco
) {
}
