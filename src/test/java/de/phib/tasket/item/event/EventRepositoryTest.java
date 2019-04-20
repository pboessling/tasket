package de.phib.tasket.item.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void findAllEvents() {
        assertThat(this.eventRepository.findAll()).isNotEmpty();
    }

    @Test
    public void findEventById_verifyTitle() {
        assertThat(this.eventRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getTitle()).isEqualTo("09:30-09:45 Daily Standup Project A");
        });
    }

    @Test
    public void findEventById_verifyCollectionTitle() {
        assertThat(this.eventRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getCollection().getTitle()).isEqualTo("Mo. 15.04.2019");
        });
    }

    /*@Test
    public void findEventsByCollectionId_verifyEventTitle() {
        assertThat(this.eventRepository.findByCollectionId("1"))
                .extracting(Event::getTitle)
                .containsExactly(
                        "09:30-09:45 Daily Standup Project A",
                        "10:30-10:45 Daily Standup Project B",
                        "11:00-12:30 Weekly Management Meeting"
                );
    }*/

    //TODO: findEventsByEventDate

}
