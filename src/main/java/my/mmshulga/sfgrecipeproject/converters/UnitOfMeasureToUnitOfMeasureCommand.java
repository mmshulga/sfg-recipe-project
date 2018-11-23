package my.mmshulga.sfgrecipeproject.converters;

import lombok.Synchronized;
import my.mmshulga.sfgrecipeproject.commands.UnitOfMeasureCommand;
import my.mmshulga.sfgrecipeproject.model.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if (source == null) return null;
        final UnitOfMeasureCommand converted = new UnitOfMeasureCommand();
        converted.setId(source.getId());
        converted.setDescription(source.getDescription());
        return converted;
    }
}
