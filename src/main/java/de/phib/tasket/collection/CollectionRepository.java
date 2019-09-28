package de.phib.tasket.collection;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CollectionRepository extends CrudRepository<Collection, String> {

    Optional<Collection> findByLocalDate(LocalDate localDate);

}
