package de.phib.tasket.item.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.phib.tasket.collection.Collection;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A task.
 */
@Entity
public class Task {

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

    /**
     * Creates a new Task.
     */
    public Task() {
    }

    /**
     * Creates a new Task.
     *
     * @param title the title of the task
     * @param status the status of the task
     */
    // TODO: In the constructor the status should always be set to TODO.
    public Task(String title, TaskStatus status) {
        this.title = title;
        this.status = status;
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

    public Collection getCollection() {
        return this.collection;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status of the task
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     *
     * @param status the status to set
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
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
                .toString();
    }

}
