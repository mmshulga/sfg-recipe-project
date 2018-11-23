package my.mmshulga.sfgrecipeproject.converters;

import lombok.Synchronized;
import my.mmshulga.sfgrecipeproject.commands.RecipeCommand;
import my.mmshulga.sfgrecipeproject.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    @Autowired
    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,
                                 IngredientCommandToIngredient ingredientConverter,
                                 NotesCommandToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) return null;

        final Recipe converted = new Recipe();
        converted.setId(source.getId());
        converted.setCookTime(source.getCookTime());
        converted.setPrepTime(source.getPrepTime());
        converted.setDescription(source.getDescription());
        converted.setDifficulty(source.getDifficulty());
        converted.setDirections(source.getDirections());
        converted.setServings(source.getServings());
        converted.setSource(source.getSource());
        converted.setUrl(source.getUrl());
        converted.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(category -> converted.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredient -> converted.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return converted;
    }
}