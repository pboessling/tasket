package de.phib.tasket.itemlist.item.event;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, String> {

    // TODO: Is this method really needed?
    // If I have the collection object already at hand, wouldn't I just rather call getEvents()?
    // List<Event> findByCollectionId(String collectionId);

}
