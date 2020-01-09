package de.phib.tasket.data.item.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for creating, reading, updating, and deleting events.
 */
@Service
public class EventService {

    private EventRepository eventRepository;

    /**
     * Creates a new instance of EventService.
     *
     * @param eventRepository an EventRepository
     */
    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Returns all events.
     *
     * @return all events
     */
    public Iterable<Event> findAll() {
        return this.eventRepository.findAll();
    }

    /**
     * Retrieves an event from the repository by id.
     *
     * @param id the id of the event
     * @return the retrieved event
     */
    public Optional<Event> findById(String id) {
        return this.eventRepository.findById(id);
    }

    /**
     * Creates or updates an event in the repository.
     *
     * @param event the event
     * @return the created or updated event
     */
    public Event save(Event event) {
        return this.eventRepository.save(event);
    }

    /**
     * Deletes an event from the repository by id.
     *
     * @param id the id of the event
     */
    public void deleteById(String id) {
        this.eventRepository.deleteById(id);
    }

}
