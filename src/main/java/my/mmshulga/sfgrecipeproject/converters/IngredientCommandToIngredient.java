package my.mmshulga.sfgrecipeproject.converters;

import lombok.Synchronized;
import my.mmshulga.sfgrecipeproject.commands.IngredientCommand;
import my.mmshulga.sfgrecipeproject.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure converter;

    @Autowired
    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure converter) {
        this.converter = converter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) return null;
        final Ingredient converted = new Ingredient();
        converted.setId(source.getId());
        converted.setAmount(source.getAmount());
        converted.setDescription(source.getDescription());
        converted.setUom(converter.convert(source.getUom()));
        return converted;
    }
}
