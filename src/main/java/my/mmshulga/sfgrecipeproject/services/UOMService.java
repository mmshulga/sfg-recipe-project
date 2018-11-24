package my.mmshulga.sfgrecipeproject.services;

import my.mmshulga.sfgrecipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UOMService {
    Set<UnitOfMeasureCommand> listAll();
}
