package com.api.recipe.services;

import com.api.recipe.dtos.recipe.RecipeDeleteData;
import com.api.recipe.dtos.recipe.RecipeDetailsData;
import com.api.recipe.dtos.recipe.RecipeRegisterData;
import com.api.recipe.dtos.recipe.RecipeUpdateData;
import com.api.recipe.models.Recipe;
import com.api.recipe.repositories.RecipeRepository;
import com.api.recipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public RecipeDetailsData save(RecipeRegisterData data) {
        var user = userRepository.getReferenceById(data.idUser());

        var recipe = new Recipe(null, data.titulo(), data.ingredientes(), data.instrucoes(), data.dificuldade(), data.porcao(), user, null);
        recipeRepository.save(recipe);
        return new RecipeDetailsData(recipe);
    }

    public Page<RecipeDetailsData> list(Pageable pagination) {
        return recipeRepository.findAll(pagination).map(RecipeDetailsData::new);
    }

    public RecipeDetailsData getRecipeById(Long id) {
        var recipe = recipeRepository.getReferenceById(id);
        return new RecipeDetailsData(recipe);
    }

    public RecipeDetailsData search(String titulo) {
        var recipe = recipeRepository.findRecipeByTitulo(titulo);
        return new RecipeDetailsData(recipe);
    }

    public RecipeDetailsData update(Long id,RecipeUpdateData data){
        var recipe = recipeRepository.getReferenceById(id);
        recipe.updateRecipe(data);
        return new RecipeDetailsData(recipe);
    }

    public void delete(RecipeDeleteData data) {
        var recipe = recipeRepository.getReferenceById(data.idRecipe());
        recipe.delete(data.reason());
    }
}
