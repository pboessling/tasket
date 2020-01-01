package de.phib.tasket.data.item.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.phib.tasket.data.collection.Collection;
import de.phib.tasket.data.shared.status.ItemStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Entity class representing a task in a daily log.
 */
@Entity
public class Task {

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
     * Creates a new Task.
     */
    public Task() {
    }

    /**
     * Returns the id of the task.
     *
     * @return the id of the task
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the task
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the title of the task.
     *
     * @return the title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status of the task
     */
    public ItemStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     *
     * @param status the status to set
     */
    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    /**
     * Returns the collection this task belongs to.
     *
     * @return the collection this task belongs to
     */
    public Collection getCollection() {
        return this.collection;
    }

    /**
     * Returns the task as String.
     *
     * @return the task as String
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("status", status)
                .append("collection", collection.getId())
                .toString();
    }

}
