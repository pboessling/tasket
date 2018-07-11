package de.phib.tasket.task;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for CRUD operations on the task database.
 */
public interface TaskRepository extends CrudRepository<Task, String> {

    /**
     * Finds a task by a given title.
     *
     * @param title the title
     * @return a task
     */
    List<Task> findByTitle(String title);

}
