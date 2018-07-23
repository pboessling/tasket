package de.phib.tasket.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for displaying, adding, editing, and deleting tasks.
 */
@Controller
public class TasksController {

    private TaskRepository taskRepository;

    /**
     * Creates a new TaskController.
     *
     * @param taskRepository a TaskRepository
     */
    @Autowired
    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Request mapping to get all tasks from the repository for displaying the task list.
     *
     * @param model the model
     * @return the name of the template to be rendered
     */
    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        Iterable<Task> tasks = taskRepository.findAll();
        TaskBoard taskBoard = new TaskBoard(tasks);

        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("taskBoard", taskBoard);

        return "showTasks";
    }

    /**
     * Request mapping to save a given task in the repository.
     *
     * @param task the task
     * @return the name of the template to be rendered
     */
    @PostMapping(path = "/tasks")
    public String saveTask(Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    /**
     * Request mapping to display the edit form for adding a new task in the repository.
     *
     * @param model the model
     * @return the name of the template to be rendered
     */
    @GetMapping("/tasks/add")
    public String addTask(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("allTaskStatus", TaskStatus.values());

        return "addTask";
    }

    /**
     * Request mapping to display the edit form for editing an existing task in the repository.
     *
     * @param model the model
     * @param id    the id of the task
     * @return the name of the template to be rendered
     */
    @GetMapping(path = "/tasks/edit/{id}")
    public String editTask(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("task", taskRepository.findById(id));
        model.addAttribute("allTaskStatus", TaskStatus.values());
        return "editTask";
    }

    /**
     * Request mapping to delete a task from the repository.
     *
     * @param id the id of the task
     * @return the name of the template to be rendered
     */
    @GetMapping(path = "/tasks/delete/{id}")
    public String deleteTask(@PathVariable(name = "id") String id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

}
