package de.phib.tasket.api;

import de.phib.tasket.item.note.Note;
import de.phib.tasket.item.note.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for providing a Web API to create, read, update, and delete notes.
 */
@RestController
@RequestMapping("/api")
public class ApiNotesController {

    private NoteRepository noteRepository;

    /**
     * Creates a new ApiNotesController.
     *
     * @param noteRepository a NoteRepository
     */
    @Autowired
    public ApiNotesController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Returns all notes.
     *
     * @return all notes
     */
    // TODO: Is this API method still needed?
    @GetMapping("/notes")
    public Iterable<Note> listAllNotes() {
        return this.noteRepository.findAll();
    }

    /**
     * Saves a given new note in the repository.
     *
     * @param newNote the new note
     * @return the new note after having been saved in the repository
     */
    @PostMapping("/notes")
    public Note createNote(@RequestBody Note newNote) {
        return this.noteRepository.save(newNote);
    }

    /**
     * Finds and returns a note from the repository by its id.
     *
     * @param id the id of the note
     * @return the note
     */
    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable("id") String id) {
        return this.noteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Note.class));
    }

    /**
     * Updates a given note in the repository.
     *
     * @param id      the id of the note
     * @param newNote the modified note
     * @return the modified note after having been saved in the repository
     */
    @PostMapping("/notes/{id}")
    public Note updateNote(@PathVariable("id") String id, @RequestBody Note newNote) {
        return this.noteRepository.findById(id)
                .map(note -> {
                    note.setTitle(newNote.getTitle());
                    return this.noteRepository.save(note);
                })
                .orElseGet(() -> {
                    newNote.setId(id);
                    return this.noteRepository.save(newNote);
                });
    }

    /**
     * Deletes a given note from the repository.
     *
     * @param id the id of the note
     */
    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable("id") String id) {
        this.noteRepository.deleteById(id);
    }

}
