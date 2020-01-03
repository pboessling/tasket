package de.phib.tasket.data.item.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Iterable<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Optional<Task> findById(String id) {
        return this.taskRepository.findById(id);
    }

    public Task save(Task task) {
        return this.taskRepository.save(task);
    }

    public void deleteById(String id) {
        this.taskRepository.deleteById(id);
    }

}
