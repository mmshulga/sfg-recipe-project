package my.mmshulga.sfgrecipeproject.repositories;

import my.mmshulga.sfgrecipeproject.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
