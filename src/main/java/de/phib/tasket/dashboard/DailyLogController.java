package de.phib.tasket.dashboard;

import de.phib.tasket.InfoAppProperties;
import de.phib.tasket.collection.Collection;
import de.phib.tasket.collection.CollectionRepository;
import de.phib.tasket.item.event.EventRepository;
import de.phib.tasket.item.note.NoteRepository;
import de.phib.tasket.item.task.Task;
import de.phib.tasket.item.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class DailyLogController {

    private CollectionRepository collectionRepository;

    private TaskRepository taskRepository;

    private EventRepository eventRepository;

    private NoteRepository noteRepository;

    private String appVersion;

    @Autowired
    public DailyLogController(CollectionRepository collectionRepository, TaskRepository taskRepository, EventRepository eventRepository, NoteRepository noteRepository, InfoAppProperties infoAppProperties) {
        this.collectionRepository = collectionRepository;
        this.taskRepository = taskRepository;
        this.eventRepository = eventRepository;
        this.noteRepository = noteRepository;
        this.appVersion = infoAppProperties.getVersion();
    }

    @GetMapping("/dailylog")
    public String renderDashboard(Model model, @ModelAttribute("editTask") Task editTask) {
        Optional<Collection> collection = collectionRepository.findById("1");

        collection.ifPresent(value -> {
            model.addAttribute("events", value.getEvents());
            model.addAttribute("tasks", value.getTasks());
            model.addAttribute("notes", value.getNotes());
        });

        // TODO: Delete, if no longer needed.
        /*Iterable<Task> tasks = taskRepository.findAll();
        Iterable<Event> events = eventRepository.findAll();
        Iterable<Note> notes = noteRepository.findAll();

        model.addAttribute("tasks", tasks);
        model.addAttribute("events", events);
        model.addAttribute("notes", notes);*/

        model.addAttribute("addTask", new Task());
        model.addAttribute("appVersion", this.appVersion);

        return "dailylog";
    }

    @GetMapping("/dashboard2")
    public String renderDashboard2(Model model, @ModelAttribute("editTask") Task editTask) {
        Iterable<Task> tasks = taskRepository.findAll();

        model.addAttribute("tasks", tasks);
        model.addAttribute("addTask", new Task());
        model.addAttribute("appVersion", this.appVersion);

        return "dashboard2";
    }

    @PostMapping(path = "/dailylog/add")
    public String saveTask(Task task) {
        taskRepository.save(task);
        return "redirect:/dashboard2";
    }

    @GetMapping(path = "/dailylog/edit/{id}")
    public String editTask(Model model, @PathVariable(value = "id") String id, RedirectAttributes attributes) {
        attributes.addFlashAttribute("editTask", taskRepository.findById(id));
        return "redirect:/dailylog";
    }

    @PostMapping(path = "/dailylog/edit/{id}")
    public String saveEditTask(Task task) {
        taskRepository.save(task);
        return "redirect:/dailylog";
    }

    @GetMapping(path = "/dailylog/delete/{id}")
    public String deleteTask(@PathVariable(name = "id") String id) {
        taskRepository.deleteById(id);
        return "redirect:/dailylog";
    }


}
