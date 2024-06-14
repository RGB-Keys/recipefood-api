package com.api.recipe.dtos.users;

import com.api.recipe.models.Address;
import com.api.recipe.models.User;

public record UserDetailsData(

        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        UserType categoria,
        Address endereco

) {
    public UserDetailsData(User user){
        this(user.getId(), user.getNome(), user.getEmail(),user.getTelefone(),user.getCpf(), user.getCategoria(), user.getEndereco());
    }
}
