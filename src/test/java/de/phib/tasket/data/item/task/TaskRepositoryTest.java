package de.phib.tasket.data.item.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void findAllTasks() {
        assertThat(this.taskRepository.findAll()).isNotEmpty();
    }

    @Test
    public void findTaskById_verifyTitle() {
        assertThat(this.taskRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getTitle()).isEqualTo("Implement Ticket 1 in Project A");
        });
    }

    @Test
    public void findtaskById_verifyCollectionTitle() {
        assertThat(this.taskRepository.findById("1")).hasValueSatisfying(it -> {
            assertThat(it.getCollection().getTitle()).isEqualTo("Mo. 15.04.2019");
        });
    }

}
