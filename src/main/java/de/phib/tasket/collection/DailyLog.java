package de.phib.tasket.collection;

import de.phib.tasket.item.event.Event;
import de.phib.tasket.item.task.Task;

import java.time.LocalDate;
import java.util.List;

public class DailyLog {

    private LocalDate localDate;

    private List<Task> tasks;

    private List<Event> events;

    public DailyLog(LocalDate localDate) {
        this.localDate = localDate;
    }
}