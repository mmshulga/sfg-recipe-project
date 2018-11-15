package my.mmshulga.sfgrecipeproject.bootstrap;

import my.mmshulga.sfgrecipeproject.model.Recipe;
import my.mmshulga.sfgrecipeproject.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RecipeBootstrap implements CommandLineRunner {
    private static final int NUMBER_TO_CREATE = 10;

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeBootstrap(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < NUMBER_TO_CREATE; i++) {
            Recipe recipe = new Recipe();
            recipe.setTitle("Wonderful recipe #" + i);
            recipe.setContent("Add something to recipe #" + i);
            recipeRepository.save(recipe);
        }
    }
}
