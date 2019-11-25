package de.phib.tasket.itemlist.item.note;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.phib.tasket.itemlist.list.collection.Collection;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Entity class representing a note in a daily log.
 */
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    @JsonBackReference
    private Collection collection;

    /**
     * Creates a new note.
     */
    public Note() {
    }

    /**
     * Returns the id of the note.
     *
     * @return the id of the note
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the note
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the title of the note.
     *
     * @return the title of the note
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the note
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the collection this note belongs to.
     *
     * @return the collection this note belongs to
     */
    public Collection getCollection() {
        return this.collection;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("collection", collection.getId())
                .toString();
    }

}
