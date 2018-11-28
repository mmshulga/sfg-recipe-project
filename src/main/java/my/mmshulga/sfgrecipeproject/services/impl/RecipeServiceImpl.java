package my.mmshulga.sfgrecipeproject.services.impl;

import lombok.extern.slf4j.Slf4j;
import my.mmshulga.sfgrecipeproject.commands.RecipeCommand;
import my.mmshulga.sfgrecipeproject.converters.RecipeCommandToRecipe;
import my.mmshulga.sfgrecipeproject.converters.RecipeToRecipeCommand;
import my.mmshulga.sfgrecipeproject.exceptions.NotFoundException;
import my.mmshulga.sfgrecipeproject.model.Recipe;
import my.mmshulga.sfgrecipeproject.repositories.RecipeRepository;
import my.mmshulga.sfgrecipeproject.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Calling getRecipes()");
        Set<Recipe> all = new HashSet<>();
        recipeRepository.findAll().forEach(all::add);
        return all;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new NotFoundException("Recipe not found!"));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe converted = recipeCommandToRecipe.convert(recipeCommand);
        Recipe saved = recipeRepository.save(converted);
        log.debug("Saved recipe with id " + saved.getId());
        return recipeToRecipeCommand.convert(saved);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}