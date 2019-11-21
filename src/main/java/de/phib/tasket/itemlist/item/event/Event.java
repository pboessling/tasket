package de.phib.tasket.itemlist.item.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.phib.tasket.itemlist.item.task.TaskStatus;
import de.phib.tasket.itemlist.list.collection.Collection;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    @JsonBackReference
    private Collection collection;

    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status;

    public Event() {
    }

    public Event(String title) {
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

    public Collection getCollection() {
        return this.collection;
    }

    /**
     * Returns the status of the event.
     *
     * @return the status of the event
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the event.
     *
     * @param status the status to set
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("status", status)
                .toString();
    }

}
