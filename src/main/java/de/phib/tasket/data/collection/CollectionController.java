package de.phib.tasket.data.collection;

import de.phib.tasket.data.item.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * API controller for creating, reading, updating, and deleting collections.
 */
@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    private CollectionService collectionService;

    /**
     * Creates a new instance of CollectionController.
     *
     * @param collectionService a CollectionService
     */
    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    // collections - start

    /**
     * Returns all collections.
     *
     * @return all collections
     */
    @GetMapping("")
    public Iterable<Collection> getAllCollections() {
        return this.collectionService.getAllCollections();
    }

    /**
     * Creates a new collection in the repository.
     *
     * @param collection the collection to create
     * @return the created collection
     */
    @PostMapping("")
    public Collection createCollection(@RequestBody Collection collection) {
        return this.collectionService.createCollection(collection);
    }

    /**
     * Retrieves a collection from the repository by id.
     *
     * @param id the id of the collection
     * @return the retrieved collection
     */
    @GetMapping("/{id}")
    public Collection getCollection(@PathVariable("id") String id) {
        return this.collectionService.getCollection(id);
    }

    /**
     * Retrieves a collection from the repository by date.
     *
     * @param date the date of the collection
     * @return the retrieved collection
     */
    @GetMapping("/byDate/{date}")
    public Collection getCollectionByDate(@PathVariable("date") String date) {
        // TODO: Handle invalid date - DateTimeParseException - 400 bad request
        // TODO: Handle invalid date - null - 400 bad request

        return this.collectionService.getCollectionByLocalDate(LocalDate.parse(date));
    }

    /**
     * Updates a collection in the repository by id.
     *
     * @param id         the id of the collection
     * @param collection the collection
     * @returnthe updated collection
     */
    @PostMapping("/{id}")
    public Collection updateCollection(@PathVariable("id") String id, @RequestBody Collection collection) {
        return this.collectionService.updateCollection(id, collection);
    }

    /**
     * Deletes a collection from the repository by id.
     *
     * @param id the id of the collection
     */
    @DeleteMapping("/{id}")
    public void deleteCollection(@PathVariable("id") String id) {
        this.collectionService.deleteCollection(id);
    }

    // collections - end

    // events - start

    /**
     * Retrieves the events of a collection from the repository.
     *
     * @param collectionId the id of the collection
     * @return the events of the collection
     */
    // TODO: Is this still needed? If not, remove it. If yes, maybe implement analog methods for tasks and notes?
    @GetMapping("/{collectiondId}/events")
    public List<Event> getEvents(@PathVariable("collectionId") String collectionId) {
        //return this.collectionService.getCollection(collectionId).getEvents();
        return this.collectionService.getEvents(collectionId);
    }

    // events - ends

}
