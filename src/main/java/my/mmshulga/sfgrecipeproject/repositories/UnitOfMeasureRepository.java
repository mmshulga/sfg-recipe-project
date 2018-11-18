package my.mmshulga.sfgrecipeproject.repositories;

import my.mmshulga.sfgrecipeproject.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
