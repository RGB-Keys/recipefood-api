package com.api.recipe.dtos.recipe;

import com.api.recipe.models.Recipe;

public record RecipeDetailsData(
        Long id,
        String titulo,
        String[] ingredientes,
        String instrucoes,
        Difficult dificuldade,
        Long porcao,
        Long idUser
) {
    public RecipeDetailsData(Recipe recipe){
        this(recipe.getId(), recipe.getTitulo(), recipe.getIngredientes(), recipe.getInstrucoes(), recipe.getDificuldade(), recipe.getPorcao(), recipe.getUsuario().getId());
    }
}
