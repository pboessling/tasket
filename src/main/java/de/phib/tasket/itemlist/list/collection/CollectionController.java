package de.phib.tasket.itemlist.list.collection;

import de.phib.tasket.itemlist.item.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// TODO: Is this Controller still needed?
@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    private CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    // collections - start

    @GetMapping("")
    public Iterable<Collection> getAllCollections() {
        return this.collectionService.getAllCollections();
    }

    @PostMapping("")
    public Collection createCollection(@RequestBody Collection collection) {
        return this.collectionService.createCollection(collection);
    }

    @GetMapping("/{id}")
    public Collection getCollection(@PathVariable("id") String id) {
        return this.collectionService.getCollection(id);
    }

    @GetMapping("/byDate/{date}")
    public Collection getCollectionByDate(@PathVariable("date") String date) {
        // TODO: Handle invalid date - DateTimeParseException - 400 bad request
        // TODO: Handle invalid date - null - 400 bad request

        return this.collectionService.getCollectionByLocalDate(LocalDate.parse(date));
    }

    @PostMapping("/{id}")
    public Collection updateCollection(@PathVariable("id") String id, @RequestBody Collection collection) {
        return this.collectionService.updateCollection(id, collection);
    }

    @DeleteMapping("/{id}")
    public void deleteCollection(@PathVariable("id") String id) {
        this.collectionService.deleteCollection(id);
    }

    // collections - end

    // events - start

    @GetMapping("/{collectiondId}/events")
    public List<Event> getEvents(@PathVariable("collectionId") String collectionId) {
        //return this.collectionService.getCollection(collectionId).getEvents();
        return this.collectionService.getEvents(collectionId);
    }

    // events - ends

}
