package de.phib.tasket.data.item.task;

import de.phib.tasket.data.shared.error.ObjectNotFoundException;
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

    private TaskService taskService;

    /**
     * Creates a new ApiTasksController.
     *
     * @param taskService a TaskService
     */
    @Autowired
    public TaskApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Returns all tasks.
     *
     * @return all tasks
     */
    // TODO: Is this API method still needed?
    @GetMapping("/tasks")
    public Iterable<Task> listAllTasks() {
        return this.taskService.findAll();
    }

    /**
     * Saves a given new task in the repository.
     *
     * @param newTask the new task
     * @return the new task after having been saved in the repository
     */
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task newTask) {
        return this.taskService.save(newTask);
    }

    /**
     * Finds and returns a task from the repository by its id.
     *
     * @param id the id of the task
     * @return the task
     */
    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") String id) {
        return this.taskService.findById(id)
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
        return this.taskService.findById(id)
                .map(task -> {
                    task.setTitle(newTask.getTitle());
                    task.setStatus(newTask.getStatus());
                    return this.taskService.save(task);
                })
                .orElseGet(() -> {
                    newTask.setId(id);
                    return this.taskService.save(newTask);
                });
    }

    /**
     * Deletes a given task from the repository.
     *
     * @param id the id of the task
     */
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable("id") String id) {
        this.taskService.deleteById(id);
    }

}
