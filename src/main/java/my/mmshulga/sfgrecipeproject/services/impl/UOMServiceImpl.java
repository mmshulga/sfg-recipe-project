package my.mmshulga.sfgrecipeproject.services.impl;

import lombok.extern.slf4j.Slf4j;
import my.mmshulga.sfgrecipeproject.commands.UnitOfMeasureCommand;
import my.mmshulga.sfgrecipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import my.mmshulga.sfgrecipeproject.repositories.UnitOfMeasureRepository;
import my.mmshulga.sfgrecipeproject.services.UOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UOMServiceImpl implements UOMService {

    private final UnitOfMeasureRepository uomRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand uomToUomcConverter;

    @Autowired
    public UOMServiceImpl(UnitOfMeasureRepository uomRepository,
                          UnitOfMeasureToUnitOfMeasureCommand uomToUomcConverter) {
        this.uomRepository = uomRepository;
        this.uomToUomcConverter = uomToUomcConverter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAll() {
        log.debug("listing all units of measure");
        return StreamSupport
                .stream(uomRepository.findAll().spliterator(), false)
                .map(uomToUomcConverter::convert)
                .collect(Collectors.toSet());
    }
}
