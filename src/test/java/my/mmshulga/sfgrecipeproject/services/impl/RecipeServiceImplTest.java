package my.mmshulga.sfgrecipeproject.services.impl;

import my.mmshulga.sfgrecipeproject.converters.RecipeCommandToRecipe;
import my.mmshulga.sfgrecipeproject.converters.RecipeToRecipeCommand;
import my.mmshulga.sfgrecipeproject.model.Recipe;
import my.mmshulga.sfgrecipeproject.repositories.RecipeRepository;
import my.mmshulga.sfgrecipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipeSet);
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipeSet.size(), recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void findRecipeByExistingId() {
        Long id = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(id);
        when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

        Recipe found = recipeService.findById(id);
        assertNotNull(found);
        assertEquals(recipe.getId(), found.getId());
        verify(recipeRepository, times(1)).findById(id);
    }

    @Test(expected = RuntimeException.class)
    public void findRecipeByNotExistingId() {
        Recipe recipe = recipeService.findById(1L);
    }
}