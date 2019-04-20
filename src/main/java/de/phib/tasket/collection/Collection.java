package de.phib.tasket.collection;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.phib.tasket.item.event.Event;
import de.phib.tasket.item.note.Note;
import de.phib.tasket.item.task.Task;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String title;

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Event> events;

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Task> tasks;

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Note> notes;

    public Collection() {
    }

    ;

    public Collection(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //TODO: Consider defensive copy
    public List<Event> getEvents() {
        return this.events;
    }

    //TODO: Consider defensive copy
    public List<Task> getTasks() {
        return tasks;
    }

    //TODO: Consider defensive copy
    public List<Note> getNotes() {
        return notes;
    }

}