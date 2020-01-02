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

    private EventService eventService;

    /**
     * Creates a new ApiEventsController.
     *
     * @param eventService an EventService
     */
    @Autowired
    public EventApiController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Returns all events.
     *
     * @return all events
     */
    // TODO: Is this API method still needed?
    @GetMapping("/events")
    public Iterable<Event> listAllEvents() {
        return this.eventService.findAll();
    }

    /**
     * Saves a given new event in the repository.
     *
     * @param newEvent the new event
     * @return the new event after having been saved in the repository
     */
    @PostMapping("/events")
    public Event createEvent(@RequestBody Event newEvent) {
        return this.eventService.save(newEvent);
    }

    /**
     * Finds and returns an event from the repository by its id.
     *
     * @param id the id of the event
     * @return the event
     */
    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable("id") String id) {
        return this.eventService.findById(id)
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
        return this.eventService.findById(id)
                .map(event -> {
                    event.setTitle(newEvent.getTitle());
                    event.setStatus(newEvent.getStatus());
                    return this.eventService.save(event);
                })
                .orElseGet(() -> {
                    newEvent.setId(id);
                    return this.eventService.save(newEvent);
                });
    }

    /**
     * Deletes a given event from the repository.
     *
     * @param id the id of the event
     */
    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable("id") String id) {
        this.eventService.deleteById(id);
    }

}
