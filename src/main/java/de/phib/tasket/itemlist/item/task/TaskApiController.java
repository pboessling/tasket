package de.phib.tasket.itemlist.item.task;

import de.phib.tasket.itemlist.shared.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for providing a Web API to create, read, update, and delete tasks.
 */
@RestController
@RequestMapping("/api")
public class TaskApiController {

    // TODO: Rename to TasksController?
    // TODO: Move request mapping path segment "tasks" up from methods to class definition?

    private TaskRepository taskRepository;

    /**
     * Creates a new ApiTasksController.
     *
     * @param taskRepository a TaskRepository
     */
    @Autowired
    public TaskApiController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Returns all tasks.
     *
     * @return all tasks
     */
    // TODO: Is this API method still needed?
    @GetMapping("/tasks")
    public Iterable<Task> listAllTasks() {
        return this.taskRepository.findAll();
    }

    /**
     * Saves a given new task in the repository.
     *
     * @param newTask the new task
     * @return the new task after having been saved in the repository
     */
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task newTask) {
        return this.taskRepository.save(newTask);
    }

    /**
     * Finds and returns a task from the repository by its id.
     *
     * @param id the id of the task
     * @return the task
     */
    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") String id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Task.class));
    }

    /**
     * Updates a given task in the repository.
     *
     * @param id      the id of the task
     * @param newTask the modified task
     * @return the modified task after having been saved in the repository
     */
    @PostMapping("/tasks/{id}")
    public Task updateTask(@PathVariable("id") String id, @RequestBody Task newTask) {
        return this.taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(newTask.getTitle());
                    task.setStatus(newTask.getStatus());
                    return this.taskRepository.save(task);
                })
                .orElseGet(() -> {
                    newTask.setId(id);
                    return this.taskRepository.save(newTask);
                });
    }

    /**
     * Deletes a given task from the repository.
     *
     * @param id the id of the task
     */
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable("id") String id) {
        this.taskRepository.deleteById(id);
    }

}
