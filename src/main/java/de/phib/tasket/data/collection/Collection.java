package de.phib.tasket.data.collection;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.phib.tasket.data.item.event.Event;
import de.phib.tasket.data.item.note.Note;
import de.phib.tasket.data.item.task.Task;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity representing a collection.
 */
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

    /**
     * Creates a new instance of Collection.
     */
    public Collection() {
    }

    /**
     * Creates a new instance of Collection.
     *
     * @param id the id of the collection
     */
    public Collection(String id) {
        this.id = id;
    }

    /**
     * Creates a new instance of Collection.
     *
     * @param localDate the date of the collection
     */
    public Collection(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Returns the id of the collection.
     *
     * @return the id of the collection.s
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the collection.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the title of the collection.
     *
     * @return the title of the collection
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the collection
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the date of the collection.
     *
     * @return the date of the collection
     */
    // TODO: Might be optional, depending on the type of Collection?
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Sets the date of the collection.
     *
     * @param localDate the date of the collection
     */
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Returns the events of the collection.
     *
     * @return the events of the collection
     */
    //TODO: Consider defensive copy
    public List<Event> getEvents() {
        return this.events;
    }

    /**
     * Returns the tasks of the collection.
     *
     * @return the tasks of the collection
     */
    //TODO: Consider defensive copy
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the notes of the collection.
     *
     * @return the notes of the collection
     */
    //TODO: Consider defensive copy
    public List<Note> getNotes() {
        return notes;
    }

}
