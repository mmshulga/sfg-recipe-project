package my.mmshulga.sfgrecipeproject.services.impl;

import lombok.extern.slf4j.Slf4j;
import my.mmshulga.sfgrecipeproject.commands.IngredientCommand;
import my.mmshulga.sfgrecipeproject.converters.IngredientCommandToIngredient;
import my.mmshulga.sfgrecipeproject.converters.IngredientToIngredientCommand;
import my.mmshulga.sfgrecipeproject.model.Ingredient;
import my.mmshulga.sfgrecipeproject.model.Recipe;
import my.mmshulga.sfgrecipeproject.model.UnitOfMeasure;
import my.mmshulga.sfgrecipeproject.repositories.RecipeRepository;
import my.mmshulga.sfgrecipeproject.repositories.UnitOfMeasureRepository;
import my.mmshulga.sfgrecipeproject.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository uomRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Autowired
    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository uomRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeAndId(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> {
            log.error("recipe with id " + recipeId + " not found");
            return new RuntimeException("recipe not found");
        });
        Optional<IngredientCommand> found = recipe.getIngredients()
                .stream()
                .filter(i -> i.getId().equals(ingredientId))
                .map(i -> ingredientToIngredientCommand.convert(i))
                .findFirst();

        return found.orElseThrow(() -> {
            log.error("ingredient with id " + ingredientId + " not found");
            return new RuntimeException("ingredient not found");
        });
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(command.getRecipeId());
        if (!optionalRecipe.isPresent()) {
            log.debug("no such recipe with id" + command.getRecipeId());
            throw new RuntimeException("no such recipe with id " + command.getRecipeId());
        }

        Recipe recipe = optionalRecipe.get();
        Optional<Ingredient> optionalIngredient = recipe.getIngredients()
                .stream()
                .filter(i -> i.getRecipe().getId().equals(command.getId()))
                .findFirst();

        if (optionalIngredient.isPresent()) {
            UnitOfMeasure ingredientUom = uomRepository.findById(command.getUom().getId())
                    .orElseThrow(() -> new RuntimeException("no existing uom found"));

            Ingredient ingredient = optionalIngredient.get();
            ingredient.setDescription(command.getDescription());
            ingredient.setAmount(command.getAmount());
            ingredient.setUom(ingredientUom);
        }
        else {
            recipe.addIngredient(ingredientCommandToIngredient.convert(command));
        }

        Recipe savedRecipe = recipeRepository.save(recipe);
        Ingredient savedIngredient = savedRecipe.getIngredients()
                .stream()
                .filter(i -> i.getId().equals(command.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("not supposed to happen"));

        return ingredientToIngredientCommand.convert(savedIngredient);
    }
}
