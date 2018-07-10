package de.phib.tasket.de.phib.tasket.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A task.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String title;

    private Calendar creationDate;

    protected Task() {}

    /**
     * Creates a new Task.
     *
     * @param title the title of the task
     */
    public Task(String title) {
        this.title = title;
        this.creationDate = new GregorianCalendar();
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
     * Returns the creation date of the task.
     *
     * @return the creation date of the task
     */
    public Calendar getCreationDate() {
        return creationDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                //.append("creationDate", creationDate)
                .toString();
    }

}
