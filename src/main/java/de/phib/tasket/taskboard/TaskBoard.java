package de.phib.tasket.taskboard;

import de.phib.tasket.itemlist.item.task.Task;
import de.phib.tasket.itemlist.item.task.TaskStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// TODO: Is this class still needed?
@Deprecated
public class TaskBoard {

    private Map<TaskStatus, List<Task>> tasks;

    public TaskBoard(Iterable<Task> tasks) {
        this.tasks = StreamSupport.stream(tasks.spliterator(), false)
                .collect(Collectors.groupingBy(Task::getStatus));
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return this.tasks.get(status);
    }

    public List<Task> getTasksTodo() {
        return this.getTasksByStatus(TaskStatus.TODO);
    }

    public List<Task> getTasksPlanned() {
        return this.getTasksByStatus(TaskStatus.PLANNED);
    }

    public List<Task> getTasksSelected() {
        return this.getTasksByStatus(TaskStatus.SELECTED);
    }

    public List<Task> getTasksDone() {
        return this.getTasksByStatus(TaskStatus.DONE);
    }

}
