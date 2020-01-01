package de.phib.tasket.data.collection;

import de.phib.tasket.data.item.event.Event;
import de.phib.tasket.data.item.note.Note;
import de.phib.tasket.data.item.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CollectionRepositoryTest {

    @Autowired
    private CollectionRepository collectionRepository;

    @Test
    public void findAllCollections() {
        assertThat(this.collectionRepository.findAll()).isNotEmpty();
    }

    @Test
    public void findCollectionById_verifyTitle() {
        assertThat(this.collectionRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getTitle()).isEqualTo("Mo. 15.04.2019");
        });
    }

    @Test
    public void findCollectionById_verifyEventTitle() {
        assertThat(this.collectionRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getEvents())
                    .extracting(Event::getTitle)
                    .containsExactly(
                            "09:30-09:45 Daily Standup Project A",
                            "10:30-10:45 Daily Standup Project B",
                            "11:00-12:30 Weekly Management Meeting"
                    );
        });
    }

    @Test
    public void findCollectionById_verifyTaskTitle() {
        assertThat(this.collectionRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getTasks())
                    .extracting(Task::getTitle)
                    .containsExactly(
                            "Implement Ticket 1 in Project A",
                            "Implement Ticket 2 in Project A",
                            "Implement Ticket 3 in Project B"
                    );
        });
    }

    @Test
    public void findCollectionById_verifyNoteTitle() {
        assertThat(this.collectionRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getNotes())
                    .extracting(Note::getTitle)
                    .containsExactly(
                            "Management Meeting: New project C in sales pipeline",
                            "Management Meeting: New employee X in Team Y",
                            "Management Meeting: All teams fully booked until end of quarter"
                    );
        });
    }

}
