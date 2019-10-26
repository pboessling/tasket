package de.phib.tasket.api;

import de.phib.tasket.item.note.Note;
import de.phib.tasket.item.note.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for providing a Web API to create, edit, and delete notes.
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

    @GetMapping("/notes")
    public Iterable<Note> listAllNotes() {
        return this.noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note newNote) {
        return this.noteRepository.save(newNote);
    }

    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable("id") String id) {
        return this.noteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Note.class));
    }

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

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable("id") String id) {
        this.noteRepository.deleteById(id);
    }

}
