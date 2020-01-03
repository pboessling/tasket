package de.phib.tasket.data.item.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Iterable<Note> findAll() {
        return this.noteRepository.findAll();
    }

    public Optional<Note> findById(String id) {
        return this.noteRepository.findById(id);
    }

    public Note save(Note note) {
        return this.noteRepository.save(note);
    }

    public void deleteById(String id) {
        this.noteRepository.deleteById(id);
    }

}
