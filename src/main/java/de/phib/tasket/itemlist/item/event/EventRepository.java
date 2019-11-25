package de.phib.tasket.itemlist.item.event;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for CRUD operations on the event database table.
 */
public interface EventRepository extends CrudRepository<Event, String> {

}
