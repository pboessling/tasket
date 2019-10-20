package de.phib.tasket.api;

import de.phib.tasket.item.task.Task;
import de.phib.tasket.item.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for providing a Web API to create, edit, and delete tasks.
 */
@RestController
@RequestMapping("/api")
public class ApiTasksController {

    private TaskRepository taskRepository;

    /**
     * Creates a new ApiTasksController.
     *
     * @param taskRepository a TaskRepository
     */
    @Autowired
    public ApiTasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public Iterable<Task> listAllTasks() {
        return this.taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task newTask) {
        return this.taskRepository.save(newTask);
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") String id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Task.class));
    }

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

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable("id") String id) {
        this.taskRepository.deleteById(id);
    }

}
