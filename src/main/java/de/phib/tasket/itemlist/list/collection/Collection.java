package de.phib.tasket.itemlist.list.collection;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.phib.tasket.itemlist.item.event.Event;
import de.phib.tasket.itemlist.item.note.Note;
import de.phib.tasket.itemlist.item.task.Task;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String title;

    @Column(name = "local_date", columnDefinition = "DATE", unique = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

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

    public Collection(String id) {
        this.id = id;
    }

    public Collection(LocalDate localDate) {
        this.localDate = localDate;
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
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
