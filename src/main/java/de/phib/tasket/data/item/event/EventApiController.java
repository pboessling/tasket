package de.phib.tasket.data.item.event;

import de.phib.tasket.data.shared.error.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for providing a Web API to create, read, update, and delete events.
 */
@RestController
@RequestMapping("/api")
public class EventApiController {

    // TODO: Rename to EventsController?
    // TODO: Move request mapping path segment "events" up from methods to class definition?

    private EventRepository eventRepository;

    /**
     * Creates a new ApiEventsController.
     *
     * @param eventRepository an EventRepository
     */
    @Autowired
    public EventApiController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Returns all events.
     *
     * @return all events
     */
    // TODO: Is this API method still needed?
    @GetMapping("/events")
    public Iterable<Event> listAllEvents() {
        return this.eventRepository.findAll();
    }

    /**
     * Saves a given new event in the repository.
     *
     * @param newEvent the new event
     * @return the new event after having been saved in the repository
     */
    @PostMapping("/events")
    public Event createEvent(@RequestBody Event newEvent) {
        return this.eventRepository.save(newEvent);
    }

    /**
     * Finds and returns an event from the repository by its id.
     *
     * @param id the id of the event
     * @return the event
     */
    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable("id") String id) {
        return this.eventRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Event.class));
    }

    /**
     * Updates a given event in the repository.
     *
     * @param id       the id of the event
     * @param newEvent the modified event
     * @return the modified event after having been saved in the repository
     */
    @PostMapping("/events/{id}")
    public Event updateEvent(@PathVariable("id") String id, @RequestBody Event newEvent) {
        return this.eventRepository.findById(id)
                .map(task -> {
                    task.setTitle(newEvent.getTitle());
                    task.setStatus(newEvent.getStatus());
                    return this.eventRepository.save(task);
                })
                .orElseGet(() -> {
                    newEvent.setId(id);
                    return this.eventRepository.save(newEvent);
                });
    }

    /**
     * Deletes a given event from the repository.
     *
     * @param id the id of the event
     */
    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable("id") String id) {
        this.eventRepository.deleteById(id);
    }

}
