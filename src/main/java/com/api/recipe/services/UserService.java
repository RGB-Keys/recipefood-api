package com.api.recipe.services;

import com.api.recipe.dtos.recipe.RecipeDeleteData;
import com.api.recipe.dtos.recipe.RecipeDetailsData;
import com.api.recipe.dtos.recipe.RecipeRegisterData;
import com.api.recipe.dtos.recipe.RecipeUpdateData;
import com.api.recipe.dtos.users.UserDetailsData;
import com.api.recipe.dtos.users.UserRegisterData;
import com.api.recipe.dtos.users.UserUpdateData;
import com.api.recipe.models.Address;
import com.api.recipe.models.Recipe;
import com.api.recipe.models.User;
import com.api.recipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsData save(UserRegisterData data) {
        var user = new User(null, data.nome(), data.email(), data.telefone(), data.cpf(), data.categoria(), new Address(data.endereco()));
        userRepository.save(user);
        return new UserDetailsData(user);
    }

    public Page<UserDetailsData> list(Pageable pagination) {
        return userRepository.findAll(pagination).map(UserDetailsData::new);
    }

    public UserDetailsData search(Long id) {
        var user = userRepository.getReferenceById(id);
        return new UserDetailsData(user);
    }

    public UserDetailsData update(Long id, UserUpdateData data){
        var user = userRepository.getReferenceById(id);
        user.updateUser(data);
        return new UserDetailsData(user);
    }

}
