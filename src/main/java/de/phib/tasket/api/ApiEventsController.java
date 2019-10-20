package de.phib.tasket.api;

import de.phib.tasket.item.event.Event;
import de.phib.tasket.item.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for providing a Web API to create, edit, and delete tasks.
 */
@RestController
@RequestMapping("/api")
public class ApiEventsController {

    private EventRepository eventRepository;

    /**
     * Creates a new ApiTasksController.
     *
     * @param eventRepository a TaskRepository
     */
    @Autowired
    public ApiEventsController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public Iterable<Event> listAllEvents() {
        return this.eventRepository.findAll();
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event newEvent) {
        return this.eventRepository.save(newEvent);
    }

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable("id") String id) {
        return this.eventRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Event.class));
    }

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

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable("id") String id) {
        this.eventRepository.deleteById(id);
    }

}
