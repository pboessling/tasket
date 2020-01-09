package de.phib.tasket.data.item.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for creating, reading, updating, and deleting notes.
 */
@Service
public class NoteService {

    private NoteRepository noteRepository;

    /**
     * Creates a new instance of NoteService.
     *
     * @param noteRepository a NoteRepository
     */
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Returns all notes.
     *
     * @return all notes
     */
    public Iterable<Note> findAll() {
        return this.noteRepository.findAll();
    }

    /**
     * Retrieves a note from the repository by id.
     *
     * @param id the id of the note
     * @return the retrieved note
     */
    public Optional<Note> findById(String id) {
        return this.noteRepository.findById(id);
    }

    /**
     * Creates or updates a note in the repository.
     *
     * @param note the note
     * @return the created or updated note
     */
    public Note save(Note note) {
        return this.noteRepository.save(note);
    }

    /**
     * Deletes a note from the repository by id.
     *
     * @param id the id of the note
     */
    public void deleteById(String id) {
        this.noteRepository.deleteById(id);
    }

}
