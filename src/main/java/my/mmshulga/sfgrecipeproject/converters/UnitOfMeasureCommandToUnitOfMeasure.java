package my.mmshulga.sfgrecipeproject.converters;

import my.mmshulga.sfgrecipeproject.commands.UnitOfMeasureCommand;
import my.mmshulga.sfgrecipeproject.model.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) return null;
        final UnitOfMeasure converted = new UnitOfMeasure();
        converted.setId(source.getId());
        converted.setDescription(source.getDescription());
        return converted;
    }
}
