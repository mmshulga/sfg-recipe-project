package my.mmshulga.sfgrecipeproject.converters;


import lombok.Synchronized;
import my.mmshulga.sfgrecipeproject.commands.NotesCommand;
import my.mmshulga.sfgrecipeproject.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) return null;
        final NotesCommand converted = new NotesCommand();
        converted.setId(source.getId());
        converted.setRecipeNotes(source.getRecipeNotes());
        return converted;
    }
}
