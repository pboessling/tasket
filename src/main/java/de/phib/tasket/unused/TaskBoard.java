package de.phib.tasket.unused;

import de.phib.tasket.data.item.task.Task;
import de.phib.tasket.data.shared.status.ItemStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// TODO: Is this class still needed?
@Deprecated
public class TaskBoard {

    private Map<ItemStatus, List<Task>> tasks;

    public TaskBoard(Iterable<Task> tasks) {
        this.tasks = StreamSupport.stream(tasks.spliterator(), false)
                .collect(Collectors.groupingBy(Task::getStatus));
    }

    public List<Task> getTasksByStatus(ItemStatus status) {
        return this.tasks.get(status);
    }

    public List<Task> getTasksTodo() {
        return this.getTasksByStatus(ItemStatus.TODO);
    }

    public List<Task> getTasksPlanned() {
        return this.getTasksByStatus(ItemStatus.PLANNED);
    }

    public List<Task> getTasksSelected() {
        return this.getTasksByStatus(ItemStatus.SELECTED);
    }

    public List<Task> getTasksDone() {
        return this.getTasksByStatus(ItemStatus.DONE);
    }

}
