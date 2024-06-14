package com.api.recipe.repositories;

import com.api.recipe.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findRecipeByTitulo(String titulo);
}
