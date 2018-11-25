package de.phib.tasket.task;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String id) {
        super("Could not find task " + id);

    }
}
