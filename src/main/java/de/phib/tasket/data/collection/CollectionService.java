package de.phib.tasket.data.collection;

import de.phib.tasket.data.item.event.Event;
import de.phib.tasket.data.shared.error.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service for creating, reading, updating, and deleting collections.
 */
@Service
@Transactional
public class CollectionService {

    private CollectionRepository collectionRepository;

    /**
     * Creates a new instance of CollectionService.
     *
     * @param collectionRepository a collectionRepository
     */
    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    /* Methods for DailyLogWebController - Start */

    /**
     * Retrieves a collection from the repository by date.
     *
     * @param localDate the date of the collection
     * @return the retrieved collection
     */
    // TODO: Can maybe replaced by de.phib.tasket.data.collection.CollectionService.getCollectionByLocalDate?
    public Optional<Collection> findByLocalDate(LocalDate localDate) {
        return this.collectionRepository.findByLocalDate(localDate);
    }

    // TODO: Remove, if no longer needed.
    /*
    public Collection save(Collection collection) {
        return this.collectionRepository.save(collection);
    }
    */
    /* Methods for DailyLogWebController - End */

    /**
     * Returns all collections.
     *
     * @return all collections
     */
    public Iterable<Collection> getAllCollections() {
        return this.collectionRepository.findAll();
    }

    /**
     * Retrieves a collection from the repository by id.
     *
     * @param id the id of the collection
     * @return the retrieved collection
     */
    public Collection getCollection(String id) {
        return this.collectionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Collection.class));
    }

    /**
     * Retrieves a collection from the repository by date.
     *
     * @param localDate the date of the collection
     * @return the retrieved collection
     */
    public Collection getCollectionByLocalDate(LocalDate localDate) {
        // TODO: Adapt ObjectNotFoundException, so that the message reflects, that the search was based on a localDate, not an id.
        return this.collectionRepository.findByLocalDate(localDate).orElseThrow(() -> new ObjectNotFoundException(localDate.toString(), Collection.class));
    }

    /**
     * Creates a new collection in the repository.
     *
     * @param collection the collection to create
     * @return the created collection
     */
    public Collection createCollection(Collection collection) {
        return this.collectionRepository.save(collection);
    }

    /**
     * Updates a collection in the repository by id.
     *
     * @param id                the id of the collection
     * @param updatedCollection the collection
     * @returnthe updated collection
     */
    public Collection updateCollection(String id, Collection updatedCollection) {
        return this.collectionRepository.findById(id)
                .map(collection -> {
                    collection.setTitle(updatedCollection.getTitle());
                    return this.collectionRepository.save(collection);
                })
                .orElseGet(() -> {
                    updatedCollection.setId(id);
                    return this.collectionRepository.save(updatedCollection);
                });
    }

    /**
     * Deletes a collection from the repository by id.
     *
     * @param id the id of the collection
     */
    public void deleteCollection(String id) {
        this.collectionRepository.deleteById(id);
    }

    /**
     * Retrieves the events of a collection from the repository.
     *
     * @param id the id of the collection
     * @return the events of the collection
     */
    // TODO: Is this still needed? If not, remove it. If yes, maybe implement analog methods for tasks and notes?
    public List<Event> getEvents(String id) {
        //TODO: Reconsider use of optional
        return this.collectionRepository.findById(id).get().getEvents();
    }
}
