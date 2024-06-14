package com.api.recipe.dtos.recipe;

public record RecipeUpdateData(
        Long id,
        String titulo,
        String[] ingredientes,
        String instrucoes,
        Difficult dificuldade,
        Long porcao
        ) {
}
