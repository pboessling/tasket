package de.phib.tasket.data.collection;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Repository for CRUD operations on the collection database table.
 */
public interface CollectionRepository extends CrudRepository<Collection, String> {

    Optional<Collection> findByLocalDate(LocalDate localDate);

}
