package de.phib.tasket.itemlist.item.note;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for CRUD operations on the note database table.
 */
public interface NoteRepository extends CrudRepository<Note, String> {

}
