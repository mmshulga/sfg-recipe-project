package my.mmshulga.sfgrecipeproject.converters;

import lombok.Synchronized;
import my.mmshulga.sfgrecipeproject.commands.CategoryCommand;
import my.mmshulga.sfgrecipeproject.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) return null;

        final CategoryCommand converted = new CategoryCommand();
        converted.setId(source.getId());
        converted.setDescription(source.getDescription());
        return converted;
    }
}
