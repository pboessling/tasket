package de.phib.tasket.data.item.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.phib.tasket.data.collection.Collection;
import de.phib.tasket.data.shared.status.ItemStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Entity class representing an event in a daily log.
 */
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String title;

    @Enumerated(EnumType.ORDINAL)
    private ItemStatus status;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    @JsonBackReference
    private Collection collection;

    /**
     * Creates a new Event.
     */
    public Event() {
    }

    /**
     * Returns the id of the event.
     *
     * @return the id of the event
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the event
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the title of the event.
     *
     * @return the title of the event
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the event
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the status of the event.
     *
     * @return the status of the event
     */
    public ItemStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the event.
     *
     * @param status the status to set
     */
    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    /**
     * Returns the collection this event belongs to.
     *
     * @return the collection this event belongs to
     */
    public Collection getCollection() {
        return this.collection;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("status", status)
                .append("collection", collection.getId())
                .toString();
    }

}
