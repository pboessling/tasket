package de.phib.tasket.data.item.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: Is this Service still needed?
@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Iterable<Event> findAll() {
        return this.eventRepository.findAll();
    }


    public Optional<Event> findById(String id) {
        return this.eventRepository.findById(id);
    }

    public Event save(Event event) {
        return this.eventRepository.save(event);
    }

    public void deleteById(String id) {
        this.eventRepository.deleteById(id);
    }

}
