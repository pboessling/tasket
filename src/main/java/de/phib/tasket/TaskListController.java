package de.phib.tasket;

import de.phib.tasket.de.phib.tasket.model.Task;
import de.phib.tasket.de.phib.tasket.model.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskListController {

    private TaskRepository taskRepository;

    public TaskListController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasklist")
    public String handleTaskList(Model model) {
        Iterable<Task> tasks = taskRepository.findAll();

        model.addAttribute("tasks", tasks);
        return "tasklist";
    }

}
