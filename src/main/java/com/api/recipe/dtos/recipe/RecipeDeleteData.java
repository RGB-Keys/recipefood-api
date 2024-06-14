package com.api.recipe.dtos.recipe;

import jakarta.validation.constraints.NotNull;

public record RecipeDeleteData(
        @NotNull
        Long idRecipe,
        @NotNull
        ReasonDelete reason
) {
}
