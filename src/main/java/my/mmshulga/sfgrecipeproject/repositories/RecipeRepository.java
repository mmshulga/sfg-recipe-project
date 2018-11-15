package my.mmshulga.sfgrecipeproject.repositories;

import my.mmshulga.sfgrecipeproject.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
