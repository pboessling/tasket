package de.phib.tasket.task;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    /**
     * Creates a new Task.
     */
    public Task() {
    }

    /**
     * Creates a new Task.
     *
     * @param title the title of the task
     */
    public Task(String title) {
        this.title = title;
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
     * Returns the task as String.
     *
     * @return the task as String
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .toString();
    }

}
