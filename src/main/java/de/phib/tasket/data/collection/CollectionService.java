package de.phib.tasket.data.collection;

import de.phib.tasket.data.item.event.Event;
import de.phib.tasket.data.shared.error.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// TODO: Is this Service still needed?
@Service
@Transactional
public class CollectionService {

    private CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    /* Methods for DailyLogWebController - Start */
    public Optional<Collection> findByLocalDate(LocalDate localDate) {
        return this.collectionRepository.findByLocalDate(localDate);
    }

    public Collection save(Collection collection) {
        return this.collectionRepository.save(collection);
    }
    /* Methods for DailyLogWebController - End */

    public Iterable<Collection> getAllCollections() {
        return this.collectionRepository.findAll();
    }

    public Collection getCollection(String id) {
        return this.collectionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Collection.class));
    }

    public Collection getCollectionByLocalDate(LocalDate localDate) {
        // TODO: Adapt ObjectNotFoundException, so that the message reflects, that the search was based on a localDate, not an id.
        return this.collectionRepository.findByLocalDate(localDate).orElseThrow(() -> new ObjectNotFoundException(localDate.toString(), Collection.class));
    }

    public Collection createCollection(Collection collection) {
        return this.collectionRepository.save(collection);
    }

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

    public void deleteCollection(String id) {
        this.collectionRepository.deleteById(id);
    }

    public List<Event> getEvents(String id) {
        //TODO: Reconsider use of optional
        return this.collectionRepository.findById(id).get().getEvents();
    }
}
