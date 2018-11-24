package my.mmshulga.sfgrecipeproject.converters;

import lombok.Synchronized;
import my.mmshulga.sfgrecipeproject.commands.IngredientCommand;
import my.mmshulga.sfgrecipeproject.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    @Autowired
    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.converter = converter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) return null;
        final IngredientCommand converted = new IngredientCommand();
        converted.setId(source.getId());
        if (source.getRecipe() != null) {
            converted.setRecipeId(source.getRecipe().getId());
        }
        converted.setAmount(source.getAmount());
        converted.setDescription(source.getDescription());
        converted.setUom(converter.convert(source.getUom()));
        return converted;
    }
}
