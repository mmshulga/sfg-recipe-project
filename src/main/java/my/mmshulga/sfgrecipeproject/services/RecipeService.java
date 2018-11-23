package my.mmshulga.sfgrecipeproject.services;

import my.mmshulga.sfgrecipeproject.commands.RecipeCommand;
import my.mmshulga.sfgrecipeproject.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
