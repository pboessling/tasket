package de.phib.tasket.data.item.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for creating, reading, updating, and deleting tasks.
 */
@Service
public class TaskService {

    private TaskRepository taskRepository;

    /**
     * Creates a new instance of TaskService.
     *
     * @param taskRepository a TaskRepository
     */
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Returns all tasks.
     *
     * @return all tasks
     */
    public Iterable<Task> findAll() {
        return this.taskRepository.findAll();
    }

    /**
     * Retrieves a task from the repository by id.
     *
     * @param id the id of the task
     * @return the retrieved task
     */
    public Optional<Task> findById(String id) {
        return this.taskRepository.findById(id);
    }

    /**
     * Creates or updates a task in the repository.
     *
     * @param task the note
     * @return the created or updated task
     */
    public Task save(Task task) {
        return this.taskRepository.save(task);
    }

    /**
     * Deletes a task from the repository by id.
     *
     * @param id the id of the task
     */
    public void deleteById(String id) {
        this.taskRepository.deleteById(id);
    }

}
