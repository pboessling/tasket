package de.phib.tasket.item.note;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void findAllNotes() {
        assertThat(this.noteRepository.findAll()).isNotEmpty();
    }

    @Test
    public void findNoteById_verifyTitle() {
        assertThat(this.noteRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getTitle()).isEqualTo("Management Meeting: New project C in sales pipeline");
        });
    }

    @Test
    public void findNoteById_verifyCollectionTitle() {
        assertThat(this.noteRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getCollection().getTitle()).isEqualTo("Mo. 15.04.2019");
        });
    }

}
