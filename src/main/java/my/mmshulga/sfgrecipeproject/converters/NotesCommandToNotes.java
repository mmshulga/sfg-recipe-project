package my.mmshulga.sfgrecipeproject.converters;

import lombok.Synchronized;
import my.mmshulga.sfgrecipeproject.commands.NotesCommand;
import my.mmshulga.sfgrecipeproject.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if (source == null) return null;
        final Notes converted = new Notes();
        converted.setId(source.getId());
        converted.setRecipeNotes(source.getRecipeNotes());
        return converted;
    }
}
