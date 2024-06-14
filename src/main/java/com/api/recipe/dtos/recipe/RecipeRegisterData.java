package com.api.recipe.dtos.recipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecipeRegisterData(
        @NotBlank String titulo,
        @NotNull String[] ingredientes,
        @NotBlank String instrucoes,
        @NotNull Difficult dificuldade,
        @NotNull Long porcao,
        @NotNull Long idUser
) {
}
