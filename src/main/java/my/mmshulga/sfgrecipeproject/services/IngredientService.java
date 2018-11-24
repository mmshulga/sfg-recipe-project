package my.mmshulga.sfgrecipeproject.services;

import my.mmshulga.sfgrecipeproject.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeAndId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
