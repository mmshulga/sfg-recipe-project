package my.mmshulga.sfgrecipeproject.bootstrap;

import my.mmshulga.sfgrecipeproject.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RecipeBootstrap implements CommandLineRunner {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeBootstrap(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
