package de.phib.tasket.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class ApiController {

    private TaskRepository taskRepository;

    @Autowired
    public ApiController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(path = "/tasks")
    public @ResponseBody
    TaskBoard getTaskBoard() {
        Iterable<Task> tasks = taskRepository.findAll();
        return new TaskBoard(tasks);
    }

}
