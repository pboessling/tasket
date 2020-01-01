package de.phib.tasket.unused;

import de.phib.tasket.data.item.event.Event;
import de.phib.tasket.data.item.task.Task;

import java.time.LocalDate;
import java.util.List;

// TODO: Currently not used? Should replace or inherit from Collection?
public class DailyLog {

    private LocalDate localDate;

    private List<Task> tasks;

    private List<Event> events;

    public DailyLog(LocalDate localDate) {
        this.localDate = localDate;
    }
}
